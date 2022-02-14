<!--- STARTEXCLUDE --->
# Workshops 📘 Better Reads 📘 !

[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/datastaxdevs/workshop-goodreads-clone)
[![License Apache2](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Discord](https://img.shields.io/discord/685554030159593522)](https://discord.com/widget?id=685554030159593522&theme=dark)

> ⚠️ Difficulty: **`Intermediate`**

Learn how to build an app end-to-end application with Spring ecosystem *(boot, mvc, security, data, test, thymeleaf)* and Apache Cassandra™.

## 📋 Table of contents

<img src="https://github.com/datastaxdevs/workshop-spring-reactive/blob/master/doc/img/ui-veterinarians.png?raw=true" align="right" width="400px"/>

1. [Objectives](#1-objectives)
2. [Acknowledgements](#2-acknowledgements)
3. [Frequently asked questions](#3-frequently-asked-questions)
4. [Materials for the Session](#4-materials-for-the-session)
5. [Create your Database](#5-create-astra-db-instance)
6. [Create your Token](#6-create-astra-token)
7. [Start and setup Gitpod](#7-start-and-setup-gitpod)
8. [Work with CqlSh](#8-work-with-cqlsh)
9. [Load Data with DSBulk](#9-load-data-with-dsbulk)
10. [Use Application in anonymous](#)
11. [Setup Github Apps](#)
12. [Authentication in the Application](#)
13. [Homeworks](#)

## 1. Objectives

* Discover how to use the following technologies: 
  * **Astra DB** (a Database-as-a-service built on Apache Cassandra)
  * **Spring Data:** the Object Mapping layer of Spring
  * **Spring Security:** how to handle authentication
  * **Spring MVC:** how to expose REST API and controllers
  * **Thymeleaf:** how to build a user interface with Spring
  * **Spring Test:** How to run tests

### 2. Acknowledgement

This application has been built based on the work of [**Java Brains**](https://www.youtube.com/channel/UCYt1sfh5464XaDBH0oH_o7Q), a famous youtuber *(500k+ subscribers)*. On his channel you can find the full [*Code with me Series*](https://www.youtube.com/watch?v=LxVGFBRpEFM), 16 episodes for building this application step-by-step. The link to each episode is provided at the end of this readme.

## 3. Frequently asked questions

<p/>
<details>
<summary><b> 1️⃣ Can I run this workshop on my computer?</b></summary>
<hr>
<p>There is nothing preventing you from running the workshop on your own machine, If you do so, you will need the following
<ol>
<li><b>git</b> installed on your local system
<li><b>JDK 8+</b> installed on your local system
<li><b>Maven 3.6+</b> installed on your local system
</ol>
</p>
In this readme, we try to provide instructions for local development as well - but keep in mind that the main focus is development on Gitpod, hence <strong>We can't guarantee live support</strong> about local development in order to keep on track with the schedule. However, we will do our best to give you the info you need to succeed.
</details>
<p/>
<details>
<summary><b> 2️⃣ What other prerequisites are required?</b></summary>
<hr>
<ul>
<li>You will need a GitHub account
<li>You will also need an Astra account: don't worry, we'll work through that in the following
</ul>
</p>
</details>
<p/>
<details>
<summary><b> 3️⃣ Do I need to pay for anything for this workshop?</b></summary>
<hr>
<b>No.</b> All tools and services we provide here are FREE.
</details>
<p/>
<details>
<summary><b> 4️⃣ Will I get a certificate if I attend this workshop?</b></summary>
<hr>
Attending the session is not enough. You need to complete the homeworks detailed below and you will get a nice badge.
</details>
<p/>

> [🏠 Back to Table of Contents](#-table-of-content)

## 4. Materials for the Session

It doesn't matter if you join our workshop live or you prefer to work at your own pace,
we have you covered. In this repository, you'll find everything you need for this workshop:

- [Slide deck](#)
- [Discord chat](https://dtsx.io/discord)
- [Questions and Answers](https://community.datastax.com/)


[🏠 Back to Table of Contents](#-table-of-content)

## 5. Create Astra DB Instance

> Leveraging [Database creation guide](https://github.com/datastaxdevs/awesome-astra/wiki/Create-an-AstraDB-Instance) create a database.

|Field|Value|
|---|---|
|**Database Name**| `workshops`|
|**Keyspace Name**| `better_reads`|

[🏠 Back to Table of Contents](#-table-of-content)

## 6. Create Astra Token

> Leveraging [Token creation guide](https://github.com/datastaxdevs/awesome-astra/wiki/Create-an-Astra-Token) create a token. (`AstraCS:blablablabla`)

|Field|Value|
|---|---|
|**Role**| `Database Amimistrator`|

## 7. Start and setup Gitpod

- Start gitpod with the button below *(right-click open in new tab)*

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/datastaxdevs/workshop-betterreads)

- Wait for the environment to initialize until you are asked to provide your token:

```
✔ Please paste the Database Admin Token here
```

- Enter your tokeb `AstraCS;blahblahblah` and press enter. Wait for all the operations to complete.

## 8. Work with CqlSh

- Interactive Cqlsh to Astra

```
/workspace/workshop-betterreads/cqlsh
```

- CQL command to Astra (not interactive)

```
set -a && source /workspace/workshop-betterreads/.env && set +a && /workspace/workshop-betterreads/tools/cqlsh-astra/bin/cqlsh -u token -p ${ASTRA_DB_ADMIN_TOKEN} -b /home/gitpod/.astra/scb_${ASTRA_DB_ID}_${ASTRA_DB_REGION}.zip \
  -e "use better_reads;describe tables;"
```

*Notes that all tables are created   MAGIC !*

## 9. Load Data with DSBulk

TBD with DSbulk 

## 6. Start the app

- Start the app

```
cd /workspace/workshop-betterreads/better-reads-webapp
mvn spring-boot:run
```


## Code with me series on JavaBrains

- [01 - Introduction to the series](https://www.youtube.com/watch?v=LxVGFBRpEFM)
- [02 - About the app](https://www.youtube.com/watch?v=HAiCwq4jfn8)
- [03 - System Design](https://www.youtube.com/watch?v=SnQXdvFkq4U)
- [04 - Cassandra Schema](https://www.youtube.com/watch?v=106jIBE9XSc)
- [05 - Setting up hosted](https://www.youtube.com/watch?v=waLSHx-VN08)
- [06 - Creating the Data Loader](https://www.youtube.com/watch?v=d28t_QySyzs)
- [07 - Connecting Spring Boot app to DataStax Astra](https://www.youtube.com/watch?v=7I37-awpaGg)
- [08 - Using Repository pattern with Spring Data](https://www.youtube.com/watch?v=uezZIPK8kPk)
- [09 - Saving all the authors in the world to Cassandra](https://www.youtube.com/watch?v=24NrLl8EhDM)
- [10 - Setting up books by ID ](https://www.youtube.com/watch?v=Fm-XrOTgOto)
- [11 - Starting with Spring boot and security](https://www.youtube.com/watch?v=nwyf_4aSkqM)
- [12 - Implementing the Book view flow](https://www.youtube.com/watch?v=-IuafzgS3fU)
- [13 - Building book search feature](https://www.youtube.com/watch?v=6K0im9vcoCk)
- [14 - Tracking user interactions with books](https://www.youtube.com/watch?v=NEZGCpN1J6M)
- [15 - Building the My Books feature](https://www.youtube.com/watch?v=ZIGImCqRr1I)
- [16 - Wrapping Up](https://www.youtube.com/watch?v=hJLtsn2aSr4)


You will now create a database with a keyspace in it (a _keyspace_ can contain _tables_.
Today's application needs just a single table: it will be created for you the first time you
will launch it, so don't worry too much).

<img src="images/create_database_button.png" width="250" align=right />
