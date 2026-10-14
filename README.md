# Demonstração de Imprecisão Numérica com `double` em Java

## Descrição

Este programa demonstra uma característica dos números de ponto flutuante em Java: a imprecisão numérica que pode ocorrer ao realizar operações aritméticas com valores decimais.

O exemplo utiliza a soma `0.1 + 0.2`, cujo resultado pode ser apresentado como `0.30000000000000004`, em vez de `0.3`.

Esse comportamento ocorre porque determinados números decimais não possuem uma representação binária finita e exata. O tipo `double` utiliza o padrão IEEE 754 para armazenar números de ponto flutuante.

O programa também apresenta uma alternativa utilizando a classe `BigDecimal`, que permite realizar operações decimais com precisão controlada.

## Objetivos

- Compreender a representação de números de ponto flutuante em Java.
- Demonstrar a imprecisão numérica em operações aritméticas.
- Identificar por que a soma `0.1 + 0.2` pode produzir um resultado diferente de `0.3`.
- Entender as limitações da comparação direta com o operador `==`.
- Conhecer a classe `BigDecimal` para realizar cálculos com valores decimais.
- Comparar os resultados obtidos com `double` e `BigDecimal`.

## Funcionamento do programa

O programa realiza duas etapas principais:

1. **Utiliza o tipo `double`:** declara as variáveis `a` e `b`, com os valores `0.1` e `0.2`, e calcula a soma. O resultado pode ser apresentado como `0.30000000000000004`. Ao comparar esse resultado com `0.3` utilizando `==`, a expressão retorna `false`.

2. **Utiliza a classe `BigDecimal`:** cria os valores decimais a partir de strings, realiza a soma com o método `add()` e compara o resultado com `0.3` utilizando o método `compareTo()`. Nesse caso, o resultado da soma é `0.3` e a comparação retorna `true`.

## Código-fonte

O arquivo `Principal.java` contém a implementação do programa, demonstrando a diferença entre a utilização de `double` e `BigDecimal` em operações com números decimais.

## Como executar

É necessário ter o Java Development Kit (JDK) e o Apache Maven instalados. O projeto contêm o arquivo `pom.xml` na raiz do diretório.

**1. Compile o projeto**

```bash
mvn compile
```

O Maven compila os arquivos-fonte do projeto e verifica se existem erros de compilação.

**2. Execute o programa**

```bash
mvn exec:java -Dexec.mainClass="Principal"
```

## Saída esperada

```text
IMPRECISAO NUMERICA EM JAVA

Utilizando double:
Soma: 0.30000000000000004
Resultado igual a 0.3? false

Utilizando BigDecimal:
Soma: 0.3
Resultado igual a 0.3? true
```

A diferença entre os resultados demonstra que a representação binária dos números de ponto flutuante pode introduzir pequenas aproximações nos cálculos.

## Conceito fundamental: representação de ponto flutuante

O tipo `double` utiliza 64 bits conforme o padrão IEEE 754:

- 1 bit para o sinal;
- 11 bits para o expoente;
- 52 bits armazenados para a fração do significando, com um bit implícito nos números normalizados.

Esse formato permite representar números em uma faixa muito ampla, mas possui precisão finita.

Alguns números decimais, como `0.1` e `0.2`, não podem ser representados exatamente em binário. Quando esses valores são utilizados em operações aritméticas, pequenas diferenças podem aparecer no resultado.

Por isso, a expressão:

```java
0.1 + 0.2 == 0.3
```

retorna `false` em Java quando avaliada com esses valores do tipo `double`.

## Comparação de números decimais

A comparação direta com `==` deve ser utilizada com cuidado em operações envolvendo `float` e `double`.

Em situações nas quais uma pequena diferença numérica é aceitável, pode-se utilizar uma tolerância:

```java
double resultado = 0.1 + 0.2;
double esperado = 0.3;
double tolerancia = 0.000000001;

boolean iguais =
        Math.abs(resultado - esperado) < tolerancia;
```

Nesse exemplo, a comparação considera os valores equivalentes quando a diferença absoluta entre eles é menor que a tolerância definida.

A escolha da tolerância depende da precisão exigida pela aplicação.

## Conceito fundamental: classe BigDecimal

A classe `BigDecimal`, disponível no pacote `java.math`, permite realizar operações aritméticas com valores decimais e controlar a precisão e o arredondamento quando necessário.

Para evitar a incorporação das aproximações binárias de um `double`, recomenda-se criar os valores a partir de strings:

```java
BigDecimal a = new BigDecimal("0.1");
BigDecimal b = new BigDecimal("0.2");

BigDecimal resultado = a.add(b);
```

A soma produz o valor decimal `0.3`.

O método `compareTo()` permite comparar os valores numericamente:

- Retorna um valor negativo quando o primeiro valor é menor.
- Retorna zero quando os valores são numericamente iguais.
- Retorna um valor positivo quando o primeiro valor é maior.

Diferentemente de `equals()`, o método `compareTo()` considera iguais valores numericamente equivalentes mesmo que tenham escalas diferentes, como `0.3` e `0.30`.

## Precisão não é o mesmo que overflow

É importante diferenciar dois conceitos:

- **Imprecisão numérica:** ocorre quando um valor não pode ser representado exatamente ou quando operações produzem aproximações.
- **Overflow:** ocorre quando o resultado ultrapassa a faixa de valores finitos que o tipo consegue representar.

Neste programa, ocorre imprecisão numérica, não overflow. O resultado permanece dentro da faixa de valores finitos do tipo `double`.

## Aplicações práticas

Compreender esse comportamento é importante em sistemas que realizam cálculos científicos, estatísticos e financeiros.

Para cálculos monetários, a classe `BigDecimal` costuma ser uma escolha apropriada, pois permite controlar a precisão decimal e as regras de arredondamento. Ainda assim, é necessário definir corretamente a escala e o arredondamento conforme as regras da aplicação.

## Conclusão

O exemplo demonstra que a soma `0.1 + 0.2` pode produzir um resultado diferente de `0.3` quando realizada com o tipo `double`. Esse comportamento decorre das limitações da representação binária dos números de ponto flutuante, e não de um defeito específico do Java.

A classe `BigDecimal` oferece uma alternativa para operações que exigem precisão decimal, especialmente em cálculos financeiros.

O principal aprendizado é que a escolha do tipo numérico deve considerar a precisão exigida pela aplicação. Nem todo número decimal pode ser representado exatamente por `float` ou `double`.