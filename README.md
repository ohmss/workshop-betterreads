<!--- STARTEXCLUDE --->
# BetterReads: A clone of Good Reads using the Spring Framework

[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/datastaxdevs/workshop-goodreads-clone)
[![License Apache2](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Discord](https://img.shields.io/discord/685554030159593522)](https://discord.com/widget?id=685554030159593522&theme=dark)

Time: *50 minutes*. Difficulty: *Intermediate*. [Start Building](#lets-start)

A full-featured Spring Application on top of Cassandra showing

* **Astra DB** (a Database-as-a-service built on Apache Cassandra)
* **Spring Data:** the Object Mapping layer of Spring
* **Spring Security:** how to handle authentication
* **Spring MVC:** how to expose REST API and controllers
* **Thymeleaf:** how to build a user interface with Spring
* **Spring Test:** How to run tests

This application has been built by [**Java Brains**](https://www.youtube.com/channel/UCYt1sfh5464XaDBH0oH_o7Q), a great youtuber. On his channel you can find the full *Code with me Series*, 16 episodes for building this application step-by-step.



<!--- ENDEXCLUDE --->

## Objectives

* Build an app end-to-end application with Spring boot **that scales** !
* Understand how to connect to Cassandra and Astra with Spring

## Frequently asked questions

- *Can I run the workshop on my computer?*

> You don't have to, it's all already in the cloud! But there is nothing preventing you from running the workshop on your own machine.
> If you do so, you will need
> * git installed on your local system
> * [node 15 and npm 7 or later](https://www.whitesourcesoftware.com/free-developer-tools/blog/update-node-js/)
> * [Python v3.8+ installed on your local system](https://www.python.org/downloads/)
>
> In this `README`, we try to provide instructions for local development as well - but keep in mind that
the main focus is development on Gitpod, hence **We can't guarantee any live support** about local development in order to keep on track with the schedule. However, we will do our best to give you the info you need to succeed.

- *What other prerequisites are required?*

> * You will need a GitHub account
> * You will also need an Astra account: don't worry about this if you don't already have one, we'll work through that in the following

- *Do I need to pay for anything for this workshop?*
> * **No.** All tools and services we provide here are FREE.

- *Will I get a certificate if I attend this workshop?*

> Attending the session is not enough. You need to complete the homeworks detailed below and you will get a nice participation certificate a.k.a. badge.


## Materials for the Session

It doesn't matter if you join our workshop live or you prefer to work at your own pace,
we have you covered. In this repository, you'll find everything you need for this workshop:

- [Workshop Video](#)
- [Slide deck](#)
- [Discord chat](https://dtsx.io/discord)
- [Questions and Answers](https://community.datastax.com/)

## Homework

TODO

# Let's start

## Table of contents

1. *Setup* - [Create your Astra DB instance](#)
2. *Setup* - [Create your Keys](#)
3. *Setup* - [Load the project into Gitpod](#)
4. *Setup* - [Setup your environment](#)
5. *Prepare DB* - [Create the Schema](#)
6. *Prepare DB* - [Load the Data](#)
7. *Application* - [Setup configuration](#)
8. *Application* - [Start the application](#)
9. [Homework instructions](#)

## Astra setup

### 2. Create your Astra DB instance

Besides the streaming platform, you'll also need a database for persistence of some
game data (the server-side representation of the "game world").

Correspondingly, you will need some connection parameters and secrets in order
to later be able to access the database.

#### 2a. Create the database

_**`ASTRA DB`** is the simplest way to run Cassandra with zero operations at all - just push the button and get your cluster. No credit card required, $25.00 USD credit every month, roughly 20M read/write operations and 80GB storage monthly - sufficient to run small production workloads._

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
