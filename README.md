# shs

[![GitHub](https://img.shields.io/badge/GitHub-Project-green.svg)](https://github.com/yourusername/IP6Syncer)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 项目简介

shs 是一个简单的工具，用于自动同步本地IPv6地址与阿里云DNS记录。当检测到本地IPv6地址与阿里云DNS记录中的IPv6地址不匹配时，它会自动更新DNS记录，并通过QQ邮箱发送通知。

## 功能特点

- **自动检测**：定期检查本地IPv6地址。
- **DNS同步**：如果本地IPv6地址与阿里云DNS记录中的IPv6地址不一致，则自动更新DNS记录。
- **邮件通知**：更新DNS记录后，通过QQ邮箱发送通知。

## 技术栈

- **语言**：Java
- **框架**：Spring Boot
- **外部服务**：阿里云DNS API, QQ邮箱SMTP服务

## 快速开始

### 预备条件

- Java 11 或更高版本
- Maven 或 Gradle
- 阿里云账号及API密钥
- QQ邮箱账号及SMTP授权码

  ## 贡献指南

欢迎贡献者提交 Pull Request 来改进此项目。如果您发现任何问题，请先查阅已有的 Issue，如果未找到相关问题，您可以新建一个 Issue。

## 许可证

本项目采用 MIT 许可证。更多信息请参见 [LICENSE](LICENSE) 文件。
