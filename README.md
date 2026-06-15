# Enterprise Apps Trang.Nguyen

## Overzicht

Dit project is een webapplicatie voor een Anderlechtse NGO die zich inzet voor gemeenschapsbouwen en hulp aan mensen die het minder breed hebben. De applicatie biedt een overzicht van evenementen, een contactpagina en informatie over de organisatie.

## Gebruikte technologieën en libraries

- **Java 25**
- **Spring Boot 4.1.0**
  - Spring Boot Starter Web (WebMVC)
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Thymeleaf
  - Spring Boot Starter Validation
  - Spring Boot Starter Mail
- **Thymeleaf** - Server-side template engine voor HTML-pagina's
- **H2 Database** - In-memory database voor het prototype
- **Tailwind CSS** (via CDN) - CSS framework voor de layout
- **Jakarta Validation** - Validatie van formulierdata
- **Maven** - Build tool

## Documentatie en tutorials

- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Thymeleaf + Spring integratie](https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Tailwind CSS Documentation](https://tailwindcss.com/docs)
- [Jakarta Bean Validation](https://jakarta.ee/specifications/bean-validation/)
- Lesmateriaal Enterprise Applications (Erasmus Hogeschool Brussel)

## AI-gebruik

AI is gebruikt als hulpmiddel om de lesstof en de gebruikte frameworks beter te begrijpen, vergelijkbaar met het raadplegen van documentatie of een studiecoach.

## Handleiding om het project uit te voeren

### Vereisten

- Java 25 (of hoger)
- Maven 3.9+

### Project starten

1. Clone de repository:
   ```bash
   git clone https://github.com/DTrang0801/Enterprise-Apps-Trang.Nguyen.git
   ```

2. Ga naar de projectmap:
   ```bash
   cd Enterprise-Apps-Trang.Nguyen
   ```

3. Start de applicatie via Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Open je browser en ga naar:
   ```
   http://localhost:8080
   ```

### Contactformulier en e-mail

Het contactformulier maakt gebruik van Mailtrap.io om e-mails fictief te versturen. De configuratie hiervoor staat in `application.properties`. Voor een werkende e-mailconfiguratie moet je je eigen Mailtrap-credentials invullen.

### Database

De applicatie gebruikt een H2 in-memory database. Bij het opstarten worden automatisch de tabellen aangemaakt. De H2-console is beschikbaar op:
```
http://localhost:8080/h2-console
```
- JDBC URL: `jdbc:h2:mem:ngo`
- Gebruiker: `sa`
- Wachtwoord: (leeg)

## Projectstructuur

```
src/main/java/com/example/enterpriseapps/
├── EnterpriseAppsApplication.java
├── controller/
│   ├── EventController.java
│   ├── AboutController.java
│   └── ContactController.java
├── model/
│   ├── Event.java
│   └── Location.java
├── repository/
│   ├── EventRepository.java
│   └── LocationRepository.java
└── dto/
    └── ContactMessage.java

src/main/resources/
├── application.properties
├── data.sql
└── templates/
    ├── layout.html
    ├── index.html
    ├── new-event.html
    ├── details.html
    ├── about.html
    └── contact.html
```
