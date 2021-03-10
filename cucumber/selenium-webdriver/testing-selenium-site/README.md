## Sobre
Testes automatizados de UI utilizando o site do Selenium como alvo.

## Palavras-Chave
Selenium WebDriver, Cucumber, JUnit, Maven.

## Arquivo .properties
Criar arquivo "test-config.properties", dentro de /src/test/resources,
com as seguintes propriedades:

    selenium-website-url=https://www.selenium.dev

    webdriver-alias=chrome
    webdriver-on-full-screen-mode=true
    webdriver-get-url-timeout=30

    absolute-path-to-chrome-webdriver=A_FORNECER
    absolute-path-to-firefox-webdriver=A_FORNECER

    selenium-previous-releases-url=https://selenium-release.storage.googleapis.com/index.html
    selenium-official-user-group-url=http://groups.google.com/group/selenium-users
    selenium-meetups-url=https://www.meetup.com/topics/selenium/