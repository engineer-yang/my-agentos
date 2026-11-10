# Changelog

记录已经发生的项目变更。计划工作见 [ROADMAP.md](ROADMAP.md)，未完成事项见 [台账](docs/TRACKING.md)。当前没有已发布版本。

## Unreleased

### Added

- GitHub 私有仓库托管及主页源码入口，地址为 https://github.com/engineer-yang/my-agentos。
- 浅色中文项目主页：五大能力、九模块架构、可复制构建命令、设计文档和路线图；支持离线访问及窄屏布局，尚未发布公网。
- CLI 独立 main 入口和可运行普通 JAR，打印 Maven 项目版本。
- 九模块 Maven 工程，固定 JDK 21 与 Spring Boot 3.5.16，根 POM 管理内部模块版本。
- Spring Boot 应用入口和启动测试；boot 打包为包含其余 8 个模块的可执行 JAR。
- 构建、测试和启动说明（docs/BUILD.md）。
- 4 份项目 HTML 文档及 18 张本地配图。
- 中英文 README、AGENTS.md 和 CLAUDE.md 开发协作入口。
- 文档索引、已确认决策、独立待办台账和统一验收清单。
- 贡献指南、路线图、Issue 模板和 Pull Request 模板。
- 项目 Git 忽略规则，保留文档跟踪并排除运行数据与密钥文件。

### Changed

- 按已确认决策统一首版 Skill/OpenAPI 范围、配置生效、密钥加载、会话与记忆边界、最终 Demo 和排期口径。
- 将 HTML 外部图片引用改为本地相对路径。
- 按项目介绍、计划能力、架构、快速开始、文档、路线图、贡献和许可证组织 README。

当前交付包含文档、仓库协作配置与构建启动骨架，尚未包含 Agent 业务功能。
