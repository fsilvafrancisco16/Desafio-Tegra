[![Build Status](https://travis-ci.com/franciscofesilva16/desafio-tegra.svg?branch=master)](https://travis-ci.com/franciscofesilva16/desafio-tegra)


#                                           API de Voos
API de desafio para processo seletivo na Tegra - Disponivel em https://api-voos.herokuapp.com/swagger-ui.html

# Desenvolvimento
Aplicação desenvolvida com linguagem Java, frameworks e padrões de desenvolvimento:
 
- Spring Boot, Spring Web e Spring MVC;
- TDD, testes unitários com JUnit e Objetos Mockito;
- MVC, aplicação em camadas;
- Monipulação de arquivos com GSon e OpenCsv;
- Documentação de API com o Swagger e Swagger UI;
- Servidor web Apache Tomcat embutido;
- Restful, não mantendo estado e fazendo uso corretos do métodos http;
- Integração contínua com o Travis CI, MAVEN e Git;
- Versionamento com Git e GitKraken.

---

## Endpoints em REST

- https://api-voos.herokuapp.com/swagger-ui.html documentação grafica com o Swagger UI.
- https://api-voos.herokuapp.com/aeroporto/todos endpoint em REST, retorna a lista de todos os aeroportos ordenados pela cidade.
- https://api-voos.herokuapp.com/voos endpoint em REST que recebe o `Aeroporto de Origem`, `Aeroporto de Destino` e `Data do voo` e retorna um JSON com todos os voos disponíveis das duas operadoras (UberAir e 99Planes) obedecendo os critérios da busca e ordenados por horário.
- As escalas de voo são exibidas desde que o horário entre os dois voos (o tempo de espera) seja à 12h e maior que 5 minutos.
- O número máximo de duas escalas.


**Exemplo de Response: AEROPORTOS**

```json
{
    "data": [
        {
            "aeroporto": "AJU",
            "nome": "Aeroporto Internacional de Aracaju",
            "cidade": "Aracaju"
        },
        {
            "aeroporto": "VIX",
            "nome": "Aeroporto Eurico de Aguiar Salles",
            "cidade": "Vitória"
        }
    ],
    "errors": []
}
```

**Exemplo de Request e Response: VOOS**
- A busca de voos por dia é um GET na `https://api-voos.herokuapp.com/voos`, com o Body seguindo este padrão em Json:
```json
{
    "de": "MCZ",
    "para": "BSB",
    "date": "2019-02-11"
}
```
- Retorno:

```json
{
    "data": [
        {
            "origem": {
                "aeroporto": "MCZ",
                "nome": "Aeroporto Internacional Zumbi dos Palmares",
                "cidade": "Maceió"
            },
            "destino": {
                "aeroporto": "BSB",
                "nome": "Aeroporto Internacional Juscelino Kubitschek",
                "cidade": "Brasília"
            },
            "data": "2019-02-11",
            "voos": [
                {
                    "voo": "M88B16",
                    "origem": {
                        "aeroporto": "MCZ",
                        "nome": "Aeroporto Internacional Zumbi dos Palmares",
                        "cidade": "Maceió"
                    },
                    "destino": {
                        "aeroporto": "BSB",
                        "nome": "Aeroporto Internacional Juscelino Kubitschek",
                        "cidade": "Brasília"
                    },
                    "saida": "2019-02-11T14:00:00",
                    "chegada": "2019-02-11T18:00:00",
                    "operadora": "Uberair",
                    "preco": 489.83
                }
            ]
        },
        {
            "origem": {
                "aeroporto": "MCZ",
                "nome": "Aeroporto Internacional Zumbi dos Palmares",
                "cidade": "Maceió"
            },
            "destino": {
                "aeroporto": "BSB",
                "nome": "Aeroporto Internacional Juscelino Kubitschek",
                "cidade": "Brasília"
            },
            "data": "2019-02-11",
            "voos": [
                {
                    "voo": "M16F34",
                    "origem": {
                        "aeroporto": "MCZ",
                        "nome": "Aeroporto Internacional Zumbi dos Palmares",
                        "cidade": "Maceió"
                    },
                    "destino": {
                        "aeroporto": "FLN",
                        "nome": "Aeroporto Internacional Hercílio Luz",
                        "cidade": "Florianópolis"
                    },
                    "saida": "2019-02-11T12:00:00",
                    "chegada": "2019-02-11T13:00:00",
                    "operadora": "Uberair",
                    "preco": 109.08
                },
                {
                    "voo": "F29B25",
                    "origem": {
                        "aeroporto": "FLN",
                        "nome": "Aeroporto Internacional Hercílio Luz",
                        "cidade": "Florianópolis"
                    },
                    "destino": {
                        "aeroporto": "BSB",
                        "nome": "Aeroporto Internacional Juscelino Kubitschek",
                        "cidade": "Brasília"
                    },
                    "saida": "2019-02-11T15:30:00",
                    "chegada": "2019-02-11T21:00:00",
                    "operadora": "Uberair",
                    "preco": 591.94
                }
            ]
        }
    ],
    "errors": []
}
```

## Execução da aplicação

- Para executar a aplicação é necessário ter instalado o JAVA na versão 8 ou superior;
- Conexão com internet, para que o Maven faça o download das dependência necessárias;
- Compilar o código fonte e gerar um `.jar`, podendo ser com uma ide como NetBeans ou Eclipse;
- Executar o projeto pela ide ou acessar pasta `/target` no home do projeto e executar `sudo java -jar DesafioTegra-0.0.1-SNAPSHOT.jar`.
- Não é necessário a instalação de um servidor web, o `.jar` já possui um servidor web Apache Tomcat embutido e por padrão vai executar na `http://localhost:8080`.



