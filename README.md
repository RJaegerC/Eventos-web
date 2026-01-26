# Projeto de Clean Architecture baseado no modelo Hexagonal

## 📌 Descrição

Este projeto tem como objetivo demonstrar, de maneira prática, como funciona o comportamento de um serviço, software ou aplicação que utiliza 
o modelo de arquitetura hexagonal, um estilo baseado nos princípios da Clean Architecture.

O projeto representa um sistema web de exposição de eventos. Os eventos são criados e exibidos em uma página onde podem 
ser pesquisados tanto eventos presenciais quanto eventos online. Cupons de desconto podem ser aplicados em eventos pagos.

Os eventos principais exibidos na página inicial são definidos com base na data mais próxima a ocorrer. 
Eventos futuros ou passados são separados em páginas diferentes, seguindo o princípio de paginação a cada 10 eventos.

## 📁 Arquitetura Hexagonal

![Modelo:](https://engsoftmoderna.info/artigos/figs/hex-ports-adapters.svg)

O padrão de arquitetura hexagonal é uma vertente da arquitetura limpa, popularmente conhecida como **Clean Architecture**. 
Uma das principais vantagens desse modelo é o desacoplamento das regras de negócio e serviços da aplicação em relação às tecnologias externas utilizadas.

Em um cenário de mudança de tecnologias ou de ambiente em um projeto, o modelo hexagonal facilita a transição de tecnologias ou ambiene 
sem prejudicar a lógica e o funcionamento dos serviços centrais, como os casos de uso e as entidades.

Esse padrão faz uso intensivo de interfaces para definir classes, repositórios e serviços de forma desacoplada de frameworks ou serviços externos. 
Posteriormente, essa lógica é integrada às tecnologias externas por meio dos **adapters**.

Os adapters são divididos em:
- **Inbound adapters**: responsáveis por receber chamadas externas (como controllers ou APIs) e direcioná-las para o núcleo da aplicação.
- **Outbound adapters**: responsáveis pela comunicação com serviços externos, como bancos de dados ou APIs de terceiros.

## ⚙️ Tecnológias utilizadas

- Java
- Spring Boot
- Amazon Web Services
- Mariadb
