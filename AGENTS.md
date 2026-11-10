# 项目开发协作规则

适用于本仓库中的开发工作。产品名称 OryxOS，仓库名称 my-agentos。默认使用中文沟通，沿用代码中的英文标识。

## 开始工作

先读 [README_CN.md](README_CN.md)、[贡献指南](CONTRIBUTING.md)、[决策](docs/DECISIONS.md)、[台账](docs/TRACKING.md) 与 [验收清单](docs/ACCEPTANCE.md)，再读任务涉及的 HTML 章节。HTML 是有效的需求来源，图示保存在 `docs/assets/`，必要时直接查看图片。

当前已完成文档和九模块 Maven 构建骨架，有一个 Spring Boot 启动测试；Agent 业务能力尚未实现。先检查实际文件和执行结果，再描述实现状态；不要从设计文档推断功能已经存在。

## 仓库布局与命令

| 路径 | 用途 |
| --- | --- |
| README.md / README_CN.md | 对外项目介绍，保持中英文状态一致 |
| CONTRIBUTING.md | 人类与 AI 贡献者共用的贡献流程 |
| ROADMAP.md / CHANGELOG.md | 计划里程碑 / 已发生变更 |
| docs/ | 原始设计、决策、待办和验收 |
| website/ | 独立静态主页；预览与验证说明见 website/README.md |
| .github/ | Issue 与 PR 模板，尚无 CI |
| pom.xml / oryxos-*/ | 根聚合 POM、8 个库模块与 boot 启动模块 |

构建基线为 JDK 21、Spring Boot 3.5.16、Maven 3.6.3+；无 Maven Wrapper。根目录运行 `mvn -B -ntp clean package`，独立测试用 `mvn -B -ntp test`，启动产物见 [构建指南](docs/BUILD.md)。只有 boot 执行 repackage；其他模块保持普通 JAR。纯文档修改检查链接、图示引用和口径一致性即可。

## 文档与变更

- 已确认口径见 DECISIONS；原始 HTML 提供上下文，ACCEPTANCE 是统一发布检查表，TRACKING 管理缺口和证据。没有被决策覆盖的新冲突，先登记并在依赖工作开始前解决，不自行挑选较省事的版本。
- 执行任务前找到对应 P/Q/A/D 编号；发现遗漏就追加台账，不删除未完成事项。
- 改变已确认范围或技术原则时，先说明影响并取得用户决策，再同步所有受影响文档。普通实现细节在既有范围内自主处理，无须重复确认。
- 只有证据满足完成条件才能关闭条目。记录命令、环境、结果、相关文件或提交；区分模拟测试、真实模型/MCP 联调、压测和业务验收。
- 不因排期超时静默删除功能。需求变化后更新决策、任务和验收。
- 不自动提交或推送 Git；保留用户和其他协作者的修改。

## 核心边界

- JDK 21 + Spring Boot 3.x 单体，9 个 Maven 模块按技术方案组织；依赖版本在 Q-01 验证后固定。
- 自实现 ReAct；Spring AI 仅做 Provider/协议适配和 Schema 生成，禁用自动工具执行。
- Provider 名与模型实例显式映射。工具实际调度统一走 ToolExecutor；工具查找和执行必须遵守当前 Profile 的工具范围。
- 内置、Java 注解和 MCP 工具统一为 OryxTool；Skill 是 ContextLoader 的指令输入，不是可执行 Tool。
- MemoryService 提供统一记忆入口；首版会话与长期两层，长期 MEMORY.md 按工作区共享，不能声称用户或 Agent 记忆已隔离。
- Session 使用独立唯一 ID；channel/user/profile 表示归属。同一组合允许多个会话。
- 首版必须持久化 sessions、tool_invocations、llm_calls；完整审计查询和治理另行扩展。
- Profile、Provider、MCP、白名单修改后重启进程；Bootstrap、Skill、长期记忆下一轮 Prompt 重新加载。
- 密钥从环境变量注入，配置仅引用变量；密钥不得进入日志、响应、示例或版本库。
- 首版是个人或可信工作区的运行时。应用层白名单不能当作完整沙箱，无认证不能当作用户隔离；外部模型与工具可能传出请求数据。

## 实施和验证

采用文档规划的 Spec-Kit 路线，但当前尚无项目内 .specify 脚手架。P-01 初始化时先确认实际 CLI/命令能力，不伪造执行记录或把手写占位文档说成已运行的工具输出。

每个 User Story 开始前检查依赖和验收，结束时检查文档一致性、实际代码和对应测试。文档分析不能替代代码审查、真实集成与验收（Q-13）。

新增非平凡逻辑应有针对性验证；修改已有功能时检查调用者和回归范围。错误处理、输入校验、数据保护不能省略。构建与测试命令以实际工程和 CONTRIBUTING.md 为准。

完成回复说明改了什么、验证了什么、仍有哪些未完成和风险。不得把 US-2 的天气查询演示当作最终“查天气并写日报”验收。

## 文件约定

仓库根 AGENTS.md/CLAUDE.md 指导开发工具，不要混入产品运行时提示词。未来 `.oryxos/AGENTS.md`、`SOUL.md`、`USER.md` 才是运行时 Bootstrap；`.oryxos/` 数据应保持在 Git 之外。

保留 HTML 的本地图片引用与原有格式。文档截图和图示可能是简化示意，具体行为以已确认文字与验收为准。
