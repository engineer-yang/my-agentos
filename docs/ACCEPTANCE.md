# 首版统一验收清单

更新日期：2026-11-10。依据：需求“核心功能/非功能需求/验收标准”、技术方案及 [DECISIONS.md](DECISIONS.md)。

**当前全部未验收。** 勾选需要对应代码、可复现命令、环境和结果；记录在本文件或链接到证据文件。模拟结果与真实服务结果分别标注。待决细节先关闭 [台账](TRACKING.md) 中相应 Q 项。

## 功能与交付

- [ ] A-01 工作区初始化：生成约定目录、默认 Profile 与 Bootstrap；密钥不写入模板。目录与数据库位置先按 Q-02 统一。
- [ ] A-02 Profile 与多 Agent：创建/列出/查看/删除，至少两个 Profile 在同一实例可用；工具范围和 Provider 映射正确；配置修改重启后生效。
- [ ] A-03 Provider：真实跑通 DeepSeek 和 Kimi 两个 Provider；显式映射，记录模型/token/错误。阶段只接通一个不代表最终完成。
- [ ] A-04 ReAct：多轮工具调用、正确保存 assistant/tool 消息、每次实际工具调用只执行一次、默认 10 次迭代上限、异常和超时行为明确；上下文不重复注入。
- [ ] A-05 Memory：save_memory 写入、recall_memory 关键词检索、新会话及重启后可用；每轮重新加载；注明工作区共享，超长与并发行为按 Q-07 验证。
- [ ] A-06 内置与 Java 工具：read_file/write_file/list_dir、Shell、http_get/http_post、save_memory/recall_memory、Java @Tool 示例；输入与 Profile 范围、路径/命令/网络白名单、超时和错误场景有验证。
- [ ] A-07 MCP 与基础 Skill：配置连接、发现、调用、错误恢复、Profile 引用；Skill 正文下一轮生效；支持范围由 Q-05 明确，运行 Demo 3。
- [ ] A-08 REST：以下 10 个端点全部可用，含参数错误、资源不存在、Provider 故障及超时行为；验证 32KB 请求上限和最近 100 条历史返回约定。
- [ ] A-09 持久化：sessions、tool_invocations、llm_calls 写入 SQLite；重启恢复；独立 session_id，同归属多个会话不串历史；归档可查；失败/并发写有验证。
- [ ] A-10 配置、密钥与日志：环境变量注入和缺失校验；日志结构化且含调用关联；真实密钥不出现在日志、响应或版本库；Bootstrap 修改下一轮生效。
- [ ] A-11 CLI 与模式：以下 12 个命令、chat/serve/gateway 三种模式和清晰的帮助/错误提示；chat 支持 --profile、--message，交互可退出；共享配置与存储行为明确。
- [ ] A-12 最小快速开始：环境要求、构建启动、环境变量、API 示例、五个 Demo 步骤和失败排查；明确共享记忆、无认证适用范围及外发条件；验证新手约 30 分钟完成单机启动。
- [ ] A-13 项目主页：可访问，说明定位、五大能力和快速开始；内容不把未实现的企业治理宣传为现有能力。
  - 2026-11-10 本地静态主页已交付并验证，见 [主页及验证说明](../website/README.md)。当前快速开始覆盖构建和版本 CLI；公网访问与发布范围尚待确定，因此暂不勾选发布验收。
- [ ] A-14 各核心功能模块至少有一个端到端用例；最终五个 Demo 均有证据。Mock 用例与真实模型/MCP 验证分别记录。

## 10 个 REST 端点

所有路径以 `/api/v1` 开头。无状态 invoke 的记忆/工具副作用按 Q-04 明确。

| 方法 | 路径 | 预期行为 |
| --- | --- | --- |
| POST | /sessions | 创建独立会话 |
| POST | /sessions/{id}/messages | 会话内处理消息 |
| GET | /sessions/{id} | 查询历史 |
| DELETE | /sessions/{id} | 归档而非物理删除 |
| POST | /agents/{name}/invoke | 单次调用 |
| GET | /profiles | 列出 Profile |
| GET | /memory | 查询工作区长期记忆 |
| GET | /tools | 查询可用工具信息 |
| GET | /health | 健康检查 |
| GET | /info | 系统信息 |

## 12 个 CLI 命令

`init`、`status`、`chat`、`serve`、`gateway`、`profile list`、`profile create`、`profile show`、`profile delete`、`provider list`、`tool list`、`session list`。

这是计划命令清单，不是当前可运行的命令。gateway 的首版接入组合与 CLI 恢复细节见 Q-04/Q-10。

## 最终五个 Demo

| 编号 | 最终验收内容 | 当前状态 |
| --- | --- | --- |
| Demo 1 | 查天气并写本地日报；检查工具调用记录及实际生成文件，不能只给穿衣建议 | 未验收 |
| Demo 2 | 第一次对话保存偏好；新建独立会话或重启后能引用记忆 | 未验收 |
| Demo 3 | Profile 引用 Skill 和外部 MCP，完成跨工具任务；PR digest 是否包含 Slack 发送先按 Q-11 确定 | 未验收，场景细节待定 |
| Demo 4 | REST 创建 Session → 发消息 → 获取响应/历史 → 归档，完整验证 | 未验收 |
| Demo 5 | 联动 info/profiles/tools/invoke/memory 五类端点完成业务流程 | 未验收 |

阶段演示：US-1/US-2 只查天气可作为中间成果；US-4 文件工具可用后补齐最终 Demo 1。每个 US 检查文档一致性并审查实际实现；不能以文档分析替代 Demo 和测试。

## 性能目标与报告

按原需求保留目标；性能不达标可以延期优化，但必须记录实测、原因和跟踪项，不得写成已达标。

- [ ] 10 个 Agent 稳定运行 4 小时。
- [ ] 100 个并发 Session。
- [ ] Session 创建 P99 < 200ms。
- [ ] 内部转发开销 < 50ms，单独标明是否排除模型与工具耗时。

报告必须列硬件、JDK/依赖、模型和工具使用真实服务还是替身、数据量、并发模型、时延分位数、错误率。技术方案中的“几千并发”等描述不作为测试证据。

## 发布结论

当前结论：未进入发布验收。基础 Skill、所有核心能力、持久化、CLI/API、最小快速开始、主页和五个 Demo 不能因排期静默删去。未解决的 Q 项在相关阶段前处理；性能例外按上节公开记录；企业治理扩展不计作首版已交付。

证据记录格式：日期、关联 A/Demo 编号、代码版本、命令、环境、实际结果、证据文件、限制及未完成事项。未执行不得勾选。
