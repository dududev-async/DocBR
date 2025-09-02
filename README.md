# DocBR
Biblioteca para validação de documentos brasileiros com suporte às regras atuais de CPF e CNPJ alfanumérico.

## Índice

- [Funcionalidades](#-Funcionalidades)

- [Instalação](#instalação-disponível-em-breve)

- [Uso/Exemplos](#usoexemplos)

- [Exceções](#-exceções)

- [Contribuindo](#contribuindo)

## Funcionalidades

- Validação de CNPJs numéricos e alfanuméricos.

- Verificação de dígitos verificadores (check digits).

- Normalização de entrada (remoção de caracteres inválidos).

- Exceções claras em casos de erro.

- API simples para uso em projetos Java/Kotlin.

## Instalação (Disponível em breve)

No maven:

```
<dependency>
  <groupId></groupId>
  <artifactId></artifactId>
  <version></version>
</dependency>
```

No gradle: 

```
implementation("groupId:artifactId:version")
```


## Uso/Exemplos

A forma mais simples de usar a api é criando um novo objeto do tipo CNPJ, sem se preocupar com espaços, pontuação ou outros caracteres inválidos — a entrada é automaticamente sanitizada durante a criação.

Em seguida, você pode realizar a validação utilizando os métodos disponíveis:


- isValid() - Retorna um boolean indicando se o CNPJ é válido ou não.
```java
public class Main {
    public static void main(String[] args) {
        CNPJ document = new CNPJ("12.345.678/ABCD-95");
        boolean valid = document.isValid();
        System.out.println(valid); // true ou false
    }
}
```

- validate() - Valida o CNPJ e retorna true se for válido ou lança uma Exception descritiva caso a validação falhe.
```java
public class Main {
    public static void main(String[] args) {
        try {
            CNPJ document = new CNPJ("12.345.POD/0001-95");
            document.validate();
        } catch (InvalidFormatException e) {
            System.out.println("Formato inválido: " + e.getMessage());
        } catch (InvalidCheckDigitException e) {
            System.out.println("Dígito verificador inválido: " + e.getMessage());
        }
    }
}
```

Também é possível utilizar os métodos de validação diretamente por meio da classe estática CNPJValidator:

```java
public class Main {
    public static void main(String[] args) {
        CNPJ document = new CNPJ("UO.345.678/0001-95");
        boolean valid = CNPJValidator.isCNPJValid(document);
        System.out.println(valid); // true ou false

        try {
            CNPJValidator.validate(document);
        } catch (InvalidFormatException e) {
            System.out.println("Formato inválido: " + e.getMessage());
        } catch (InvalidCheckDigitException e) {
            System.out.println("Dígito verificador inválido: " + e.getMessage());
        }
    }
}
```

## 📛 Exceções

A biblioteca define exceções específicas para informar os motivos pelos quais um CNPJ pode ser considerado inválido. Isso ajuda a tratar cada erro de forma clara e precisa.

1️⃣ InvalidCNPJFormatException

Descrição: Lançada quando o CNPJ informado não possui o formato esperado (alfanumérico, comprimento correto, etc.).

Exemplo:

```java
CNPJ documentA = new CNPJ("12.34A.3/5678-90"); //numero de digitos menor que 14
CNPJ documentB = new CNPJ("12.34A.3BT/5678-A9"); //caracteres alfanumericos no DV
```

2️⃣ InvalidCNPJCheckDigitException

Descrição: Lançada quando os dígitos verificadores (check digits) não correspondem ao cálculo oficial. Para mais informações sobre a regras de cálculo oficial, seguir o link da [Receita Federal](https://www.gov.br/receitafederal/pt-br/centrais-de-conteudo/publicacoes/documentos-tecnicos/cnpj).


## Contribuindo

Contribuições são sempre bem-vindas!

Veja `contribuindo.md` para saber como começar.
