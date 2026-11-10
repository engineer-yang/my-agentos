# Maven 工程与构建

本次范围：按照技术方案第 10 章初始化九模块 Maven 工程，验证编译、测试、打包及启动。用户已明确授权这一步；它先于完整 Spec-Kit 初始化，不能据此关闭 P-01 的规格产物任务。

## 环境与坐标

- JDK 21。
- Maven 3.6.3 及以上；本机使用 Maven 3.8.1。
- Spring Boot 3.5.16，由根 POM 的 `spring-boot-starter-parent` 固定依赖与插件管理。
- 坐标：`io.oryxos:oryxos:0.1.0-SNAPSHOT`。这是本地开发坐标，不代表已发布制品或域名归属。

选型参考：[Spring Boot 3.5 系统要求](https://docs.spring.io/spring-boot/3.5/system-requirements.html)、[Maven 插件与父 POM](https://docs.spring.io/spring-boot/3.5/maven-plugin/using.html)。JDK 21 是本项目要求，不是 Spring Boot 3.x 的最低 JDK 要求。

## 模块结构

| 模块 | 当前工程职责 | Maven 内部依赖 |
| --- | --- | --- |
| oryxos-core | 核心引擎与接口的包结构 | 无 |
| oryxos-provider | 模型接入的包结构 | core |
| oryxos-memory | 记忆服务的包结构 | core |
| oryxos-tool | 工具与 MCP 的包结构 | core |
| oryxos-web | REST 接入的包结构 | core |
| oryxos-channel-cli | 交互式 CLI 渠道的包结构 | core |
| oryxos-storage | 数据持久化的包结构 | core |
| oryxos-cli | CLI main 入口，打印项目版本 | core |
| oryxos-boot | Spring Boot 入口、装配与启动测试 | 其他 8 个模块 |

根工程 packaging 为 pom；8 个库模块输出普通 JAR；只有 boot 执行 Spring Boot repackage，生成包含其他模块的可执行 JAR。core 不反向依赖具体能力模块，不形成模块环。

本次只建立构建依赖关系；CLI 是直接调核心服务还是经 HTTP 接入仍在 Q-10 待定。Spring AI/Alibaba、SQLite/JPA、MCP、Picocli 和 Web starter 会在对应功能实施时引入，不在空工程中启用外部服务的自动配置。

## 构建步骤

在项目根目录执行：

```sh
java -version
mvn -version
mvn -B -ntp clean package
```

必须确认 `mvn -version` 中 Java 为 21。首次构建需要下载 Maven Central 依赖；使用已有 Maven 设置，不在仓库写入个人镜像、凭证或本机 JDK 路径。

独立运行测试：

```sh
mvn -B -ntp test
```

只构建启动模块及其依赖：

```sh
mvn -B -ntp -pl oryxos-boot -am package
```

## 启动骨架

```sh
java -jar oryxos-boot/target/oryxos-boot-0.1.0-SNAPSHOT.jar
```

当前只有 Spring Boot 上下文，没有 Web 服务或 CLI 交互循环，启动后自然退出属于预期行为。不监听端口，不需要模型密钥或数据库。这不是产品 `chat/serve/gateway` 命令的交付。

其他模块的 JAR 位于各自 `target/` 目录。除 CLI 版本入口外，当前库模块只有包说明，业务类型尚未实现。不要将 `target/` 产物提交到 Git。

## CLI 版本入口

构建后可直接执行：

```sh
java -jar oryxos-cli/target/oryxos-cli-0.1.0-SNAPSHOT.jar
```

输出 `OryxOS 0.1.0-SNAPSHOT` 后退出。IDE 中可运行 `io.oryxos.cli.OryxOsCli.main()`；需要先完成 Maven 资源处理。版本由 Maven 将 `project.version` 写入资源文件，不在 Java 中维护重复版本号。

CLI 仍是普通 JAR，只增加 manifest 主类，不启动 Spring，也不执行 Boot repackage；当前版本打印仅使用 JDK。后续命令若需要其他模块，须补充运行时依赖装配。交互和命令解析尚未实现。

## 验证范围与记录

2026-11-10 本地验证完成，对应 [台账 B-01](TRACKING.md)。实际环境为 Temurin 21.0.11、Maven 3.8.1。

| 检查 | 结果 / 证据 |
| --- | --- |
| `mvn -B -ntp clean package` | BUILD SUCCESS；根聚合工程及 9 个模块全部 SUCCESS |
| Spring Boot 上下文测试 | 1 项通过，0 失败、0 错误、0 跳过；报告位于 `oryxos-boot/target/surefire-reports/` |
| 模块与产物 | 9 个模块依赖无环，9 个 JAR 可读取；boot 包含其他 8 个模块 |
| 编译与入口 | 主类 class major version 为 65（Java 21）；manifest 使用 JarLauncher 和 OryxOsApplication |
| 上述 `java -jar` 命令 | 输出 `Started OryxOsApplication`，退出码为 0 |

启动测试期间 Mockito/Byte Buddy 会产生 Java 21 动态加载 agent 的警告，本次不影响测试结果；升级 JDK 或测试依赖时再核对其兼容性。

运行时功能、REST 端点、12 个 CLI 命令、Provider/MCP 联调、SQLite 持久化以及五个 Demo 均不在本次构建初始化的完成声明中。
