<!--- STARTEXCLUDE --->
# Workshops 📘 Better Reads 📘 !

<img src="img/badge.png?raw=true" width="150" align="right" />

[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/datastaxdevs/workshop-goodreads-clone)
[![License Apache2](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Discord](https://img.shields.io/discord/685554030159593522)](https://discord.com/widget?id=685554030159593522&theme=dark)

> ⚠️ Difficulty: **`Intermediate`**

Learn how to build an app end-to-end application with Spring ecosystem *(boot, mvc, security, data, test, thymeleaf)* and Apache Cassandra™.

[🏁 Access HANDS-ON](#-start-hands-on)

## 📋 Table of content

<img src="https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/screenshot.png?raw=true" align="right" width="400px"/>

1. [Objectives](#1-objectives)
2. [Acknowledgements](#2-acknowledgements)
3. [Frequently asked questions](#3-frequently-asked-questions)
4. [Materials for the Session](#4-materials-for-the-session)
5. [Create your Database](#5-create-astra-db-instance)
6. [Create your Token](#6-create-astra-token)
7. [Start and setup Gitpod](#7-start-and-setup-gitpod)
8. [Work with CqlSh](#8-work-with-cqlsh)
9. [Load Data with DSBulk](#9-load-data-with-dsbulk)
10. [Use Application in anonymous](#10-use-application-in-anonymous)
11. [Setup Github Apps](#11-setup-github-apps)
12. [Authenticate and use application](#12-authenticate-and-use-application)
13. [Homeworks](#13-homeworks)

## 1. Objectives

* Discover how to use the following technologies: 
  * **Astra DB** (a Database-as-a-service built on Apache Cassandra)
  * **Spring Data:** the Object Mapping layer of Spring
  * **Spring Security:** how to handle authentication
  * **Spring MVC:** how to expose REST API and controllers
  * **Thymeleaf:** how to build a user interface with Spring
  * **Spring Test:** How to run tests

## 2. Acknowledgements

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

----

# 🏁 Start Hands-on

## 5. Create Astra DB Instance

> Leveraging [Database creation guide](https://github.com/datastaxdevs/awesome-astra/wiki/Create-an-AstraDB-Instance) create a database.

|Field|Value|
|---|---|
|**Database Name**| `workshops`|
|**Keyspace Name**| `better_reads`|

If you already have a DB, you may need to resume it using the [Resuming Guide](https://github.com/datastaxdevs/awesome-astra/wiki/How-to-resume-a-database) and simply add the `better_reads` keyspace.

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

- Enter your token `AstraCS;blahblahblah` and press enter. Wait for all the operations to complete.

```bash
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  18.676 s
[INFO] Finished at: 2022-02-14T13:00:07Z
[INFO] ------------------------------------------------------------------------
```

## 8. Work with CqlSh

#### ✅ 8a. Open new Terminal

It would be handy to have access to this CQLSH while doing the exercises and check the content of the database.

- Open a new terminal with the proper icon as show below

![new_terminal2](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/new-terminal1.png?raw=true)

or 

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/new-terminal2.png?raw=true)

#### ✅ 8b. Enter the interactive Cqlsh *(it is a script we have created for you)*

```
/workspace/workshop-betterreads/cqlsh
```

- It should look like

```
Connected to cndb at 127.0.0.1:9042.
[cqlsh 6.8.0 | Cassandra 4.0.0.6816 | CQL spec 3.4.5 | Native protocol v4]
Use HELP for help.
token@cqlsh>
```

- List Keyspaces with 

```sql
describe keyspaces;
```

- Check that our keyspace `better_reads` is there

```
token@cqlsh> describe keyspaces;

system_virtual_schema  system_auth   data_endpoint_auth  system_traces
temporal_visibility    system_views  better_reads        ecommerce    
netflix                system        spring_petclinic    todos        
system_schema          datastax_sla  native_java         feeds_reader 

token@cqlsh> 
```

- List `better_reads` tables

```
use better_reads;
describe tables;
```

- Check that expected tables are there

```bash
token@cqlsh:better_reads> describe tables;

author_by_id  books_by_user  book_by_id  book_by_user_and_bookid
```

`DARK MAGIC !`

[🏠 Back to Table of Contents](#-table-of-content)

## 9. Load Data with DSBulk

- Check you have the dataset ready. In the `BASH` terminal (not the cqlsh one). You should see the cql file `book_by_id_0.csv`.

```
ls -l /workspace/workshop-betterreads/dataset/
```

- Check how many rows. It should have more than 250k.

```
wc -l /workspace/workshop-betterreads/dataset/book_by_id_0.csv
```

- Check that Datastax bulk loader is properly installed *(Dark magic again)*

```
/workspace/workshop-betterreads/tools/dsbulk-1.8.0/bin/dsbulk --version
```

- Import the DataSet with the following command

```
/workspace/workshop-betterreads/tools/dsbulk-1.8.0/bin/dsbulk load \
   -c csv \
   -k better_reads \
   -t book_by_id \
   -u token \
   -p ${ASTRA_DB_ADMIN_TOKEN} \
   -b /home/gitpod/.astra/scb_${ASTRA_DB_ID}_${ASTRA_DB_REGION}.zip \
   -url /workspace/workshop-betterreads/dataset/book_by_id_0.csv
```

- The batch is running and should be able to see the throughput at 3700 records per second.

```
Picked up JAVA_TOOL_OPTIONS:  -Xmx2576m
Username and password provided but auth provider not specified, inferring PlainTextAuthProvider
A cloud secure connect bundle was provided: ignoring all explicit contact points.
A cloud secure connect bundle was provided and selected operation performs writes: changing default consistency level to LOCAL_QUORUM.
Operation directory: /workspace/workshop-betterreads/logs/LOAD_20220214-132501-509314
 total | failed | rows/s | p50ms | p99ms | p999ms | batches
17,152 |      0 |  3,074 | 22.68 | 58.98 | 103.81 |    1.00
```

> TODO ⚠️ THE INPUT FILE HAS STILL 0.1% error and import will FAILED AFTER 150k records 


[🏠 Back to Table of Contents](#-table-of-content)

## 10. Use Application in anonymous

- It would be handy to know the URL of the application

```
gp url 8080
```

- Start the app

```
cd /workspace/workshop-betterreads/better-reads-webapp
mvn spring-boot:run
```

- Output

```

 ________          __                   __                  ________                     .__                                     
 \______ \ _____ _/  |______    _______/  |______  ___  ___ \______ \   _______  __ ____ |  |   ____ ______   ___________  ______
  |    |  \\__  \\   __\__  \  /  ___/\   __\__  \ \  \/  /  |    |  \_/ __ \  \/ // __ \|  |  /  _ \\____ \_/ __ \_  __ \/  ___/
  |    `   \/ __ \|  |  / __ \_\___ \  |  |  / __ \_>    <   |    `   \  ___/\   /\  ___/|  |_(  <_> )  |_> >  ___/|  | \/\___ \ 
 /_______  (____  /__| (____  /____  > |__| (____  /__/\_ \ /_______  /\___  >\_/  \___  >____/\____/|   __/ \___  >__|  /____  >
         \/     \/          \/     \/            \/      \/         \/     \/          \/            |__|        \/           \/ 

 BetterReads with Spring Boot, String Data, Spring NVC, Spring security
 An application by JabaBrains.


 The application will start at http://localhost:8080


13:37:20.276 INFO  com.datastax.astra.sdk.AstraClient              : Setup of AstraClient from application.yml
13:37:20.280 INFO  com.datastax.astra.sdk.config.AstraClientConfig : Initializing [AstraClient]
13:37:20.459 INFO  com.datastax.astra.sdk.AstraClient              : + API(s) Devops     [ENABLED]
13:37:20.459 INFO  com.datastax.astra.sdk.AstraClient              : + Db: id [3ed83de7-d97f-4fb6-bf9f-82e9f7eafa23] and region [eu-west-1]
13:37:20.460 INFO  com.datastax.astra.sdk.AstraClient              : + Downloading bundles in: [/home/gitpod/.astra]
13:37:21.124 INFO  com.datastax.astra.sdk.databases.DatabaseClient : + SecureBundle found : scb_3ed83de7-d97f-4fb6-bf9f-82e9f7eafa23_eu-west-1.zip
13:37:21.124 INFO  com.datastax.astra.sdk.databases.DatabaseClient : + SecureBundle found : scb_3ed83de7-d97f-4fb6-bf9f-82e9f7eafa23_eu-central-1.zip
13:37:23.041 INFO  com.datastax.astra.sdk.AstraClient              : [AstraClient] has been initialized.
```

- In a new tab open the application with the above url

```
gp preview $(gp url 8080)
```

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/app1.png?raw=true)

- In the search item look for `Glimpses of ancient Sowams` you can search to whatever you want it will request open library ut during this workshop you only imported a subset of books, let us pick one you imported

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/app2.png?raw=true)

- Select the first item, if you select the second you will hit the page book not found as this book is not in the DB

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/app3.png?raw=true)

This is only what we can do at this point. To mark the book as read we will need to authenticate against `GITHUB`. 


[🏠 Back to Table of Contents](#-table-of-content)

## 11. Setup Github Apps

Default settings in `application.yml` use an application from `DatastaxDevelopers` but it must run locally.

As each attendee has a different URL in gitpod you will have to create your own github APPS. Let's do this together.

- During github setting we will have to enter a callback URL. To know which one enter use the following command

```
clear
echo $(gp url 8080)/login/oauth2/code/github
```

- Login to your github account and go to `Organizations`

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/githubapps1.png?raw=true)

- There scroll down to locate the last item of the menu `Developer Settings` *(hopefully you have not as many organizations as me)*

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/githubapps2.png?raw=true)

- Click button `[New Github Apps]` on the page

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/githubapps3.png?raw=true)

- You will be asked to login again for security reasons, then fill the form to register a new Github App

|Name| Value|
|---|---|
| `Github App name`| The application name shown to user |
| `Homepage URL`| Can be anything, just the app (gp url 8080) |
| `CallbackURL`| Call back url the one listed above |


![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/githubapps4.png?raw=true)

- Uncheck the Webhook active box

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/githubapps5.png?raw=true)

- In user permission put the email adress and read and profile as red/writes.

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/githubapps6.png?raw=true)

- Allow `Any Account` to access

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/githubapps7.png?raw=true)

- You got now your `Client ID`, check here it looks like `lv1....`. Click Generate a new client secret

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/githubapps8.png?raw=true)

- Now you have it all time to update the application

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/githubapps9.png?raw=true)

- Save the change

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/githubapps10.png?raw=true)


```
You are now doomed we will now mine cryptos with your github account 
Just kidding.
``` 

- Open `application.yml`

```
gp open /workspace/workshop-betterreads/better-reads-webapp/src/main/resources/application.yml
```

- Changes keys `client-id` and `client-secret` with your values

![new_terminal](https://github.com/datastaxdevs/workshop-betterreads/blob/master/img/githubapps11.png?raw=true)

[🏠 Back to Table of Contents](#-table-of-content)

## 12. Authenticate and use application

[🏠 Back to Table of Contents](#-table-of-content)

## 13. Homeworks

<img src="img/badge.png?raw=true" width="200" align="right" />

Don't forget to complete your assignment and get your verified skill badge! Finish and submit your homework!

1. Complete the practice steps as described below until you have your own app running in Gitpod.
2. Answer the technical questions in the form (We promise, it is NOT difficult if you follow the workshop).
3. Take a screenshot of you authenticated in the app with a few books
4. Submit your homework [here](https://dtsx.io/homework-betterreads)

5. *(totally optional)* Watch the full course on Javabrains.io

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

----
