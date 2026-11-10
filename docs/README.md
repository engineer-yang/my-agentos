# 文档索引

更新日期：2026-11-10。九模块 Maven 构建骨架已就绪，Agent 业务功能尚未实现。

## 开发依据

项目入口采用常见仓库分工：[README](../README_CN.md) 介绍项目，[CONTRIBUTING](../CONTRIBUTING.md) 说明贡献流程，[ROADMAP](../ROADMAP.md) 列里程碑，[CHANGELOG](../CHANGELOG.md) 记录已发生变更，详细决策与待办保留在本目录。

| 文档 | 用途 |
| --- | --- |
| [业界调研](业界调研.html) | 定位、行业判断、长期愿景；外部事实尚未重新核验 |
| [需求文档](需求文档.html) | 功能和非功能需求、首版与扩展边界 |
| [技术方案](技术方案文档.html) | 技术栈、9 模块、运行链路、存储 |
| [AI 编程指南](AI%20编程指南.html) | Spec-Kit 主体实施和后续增量协作思路 |
| [已确认决策](DECISIONS.md) | 2026-11-10 用户确认的口径及其影响 |
| [不一致与待办台账](TRACKING.md) | 已解决冲突、未决事项、待实现功能及延期工作 |
| [统一验收清单](ACCEPTANCE.md) | 功能、Demo、工程交付和性能证据 |
| [构建指南](BUILD.md) | Maven 模块、固定版本、编译测试打包及启动 |
| [项目主页](../website/index.html) / [维护说明](../website/README.md) | 本地可浏览的项目介绍、架构和构建入口；待公网发布 |

已确认的修订同步回 HTML。新冲突没有既定决策时先登记台账，不按文档顺序或更新时间自行裁决。验收清单汇总已确认要求；尚未确定的细节标记关联 Q 项，不伪装成已经完成的设计。

## 文档和图片

4 份 HTML 的 18 张原始 PNG 已保存到 [assets](assets/)，引用均为相对路径。图片命名：research 4 张、requirements 4 张、architecture 6 张、ai-guide 4 张。搬运文档时保留这个目录结构。

图片主要复述正文，没有发现会改变开发范围的独有需求。原里程碑图仍保留“4 周 / 12 小时”文字，表示历史估算；已在 HTML 相邻文字说明它不是交付承诺（D-06）。不修改原图像素。

## 当前交付状态

- 已完成：图片本地化、确认口径修订、基础协作文档、九模块 Maven 骨架、编译打包及启动测试。
- 未完成：Spec-Kit 项目初始化、Agent 运行时业务能力、完整产品快速开始、真实集成及发布验收。
- 不把本文档包当作正式 Spec-Kit 输出；后续 P-01 从这些来源生成并检查 constitution/spec/plan/tasks。

根目录入口：[中文 README](../README_CN.md)、[English README](../README.md)、[AGENTS.md](../AGENTS.md)、[CLAUDE.md](../CLAUDE.md)。

## 基础文档组织参考

2026-11-10 参考以下官方文档与开源仓库的组织方式，仅借鉴结构，不复制其代码、品牌或项目能力声明：

- [GitHub README 指南](https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes)：项目用途、入门、帮助和贡献入口，使用相对链接。
- [GitHub 贡献指南约定](https://docs.github.com/en/communities/setting-up-your-project-for-healthy-contributions/setting-guidelines-for-repository-contributors)：CONTRIBUTING 文件与 Issue/PR 指引。
- [Spring AI Alibaba README](https://github.com/alibaba/spring-ai-alibaba/blob/main/README.md)：简介、架构、能力、Getting Started 和项目文档。
- [LangChain4j README](https://github.com/langchain4j/langchain4j/blob/main/README.md) 与 [贡献指南](https://github.com/langchain4j/langchain4j/blob/main/CONTRIBUTING.md)：文档、示例与贡献分工，以及提交变更时的验证说明。

本项目已托管到 [GitHub 私有仓库](https://github.com/engineer-yang/my-agentos)，尚无发布版本、CI 或已选许可证，因此不添加对应的成功徽章、下载地址或虚构联系方式。贡献指南为当前阶段最小版本，完整社区治理仍按台账后续完善。
