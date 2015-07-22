# REmail #

Software system developers have to communicate about the project they are building. Especially when they work in open source projects and they are spread all over the world, **developers use emails to communicate**.

Studies tell us that emails are, by far, the most used means of communication used during the development, opposed to instant messaging, commits, or code comments. Therefore, we can imagine that development mailing lists contain essential information concerning various entities of the source code, that unfortunately gets lost with time, since they are not easy to retrieve.

**REmail** is an Eclipse plugin we are developing that **integrates email communication in the IDE** to get the most out of it. Using the current release, REmail allows a developer, without ever exiting from the Eclipse IDE, to select a Java class, automatically retrieve all the related messages from a specified email archive, and read them in a convenient way. REmail is currently under heavy development, but the basic functionality of the plugin has been established.

![http://r-email.googlecode.com/svn/wiki/images/RemailScreenshot.png](http://r-email.googlecode.com/svn/wiki/images/RemailScreenshot.png)


REmail uses a number of lightweight methods to link the code with emails, that had first been introduced by [Alberto Bacchelli](http://www.inf.usi.ch/phd/bacchelli/), [Michele Lanza](http://www.inf.usi.ch/faculty/lanza/), [Marco D'Ambros](http://www.inf.usi.ch/phd/dambros/), and [Romain Robbes](http://www.dcc.uchile.cl/~rrobbes/). You can set the plugin to use all of these methods, from the simple search for the class name to more elaborate methods using complex regular expressions (a detailed description of each method can be found in [Benchmarking Lightweight Techniques to Link E-Mails and Source Code](http://www.inf.usi.ch/phd/bacchelli/publications/wcre2009.pdf) and [Linking E-Mails and Source Code Artifacts](http://www.inf.usi.ch/phd/bacchelli/publications/icse2010.pdf)).

**REmail** stores emails using the database couchDB. To import email in it, there are three possible ways:
  * from **Mbox file**
  * from **MarkMail** (a web archive of mailing lists)
  * automatically import newly arrived email using a **POP client**
REmail doesn't not allow only to show emails, but have may other different features, such as the possibility to **send emails**, mark important emails by a new system of **ratings**, and can also show you some nice charts.

Feel free to **[try REmail](http://code.google.com/p/r-email/downloads)** and tell us what you think about it and what features would you like to see. We would really appreciate! You can find documentation on how to use REmail **[here](http://remail.inf.usi.ch/documentation.html)**.

More about REmail and related reseach in [Towards Integrating E-Mail Communication in the IDE](http://www.inf.usi.ch/phd/bacchelli/publications/suite2010.pdf), a short paper introducing the plugin, and in [RTFM (Read The Factual Mails) - Augmenting Program Comprehension with Remail](http://www.inf.usi.ch/phd/bacchelli/publications/csmr2011.pdf), another paper that discusses REmail in its current glance as well as it analyses how it can help to understand a system.