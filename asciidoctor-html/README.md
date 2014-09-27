This the example application of a blog post that describes how you can transform Asciidoc documents into HTML
and render the created HTML in a Spring MVC web application.

You can read the blog post at:

* [Using Asciidoctor with Spring: Rendering Asciidoc Documents with Spring MVC](http://www.petrikainulainen.net/programming/asciidoctor/using-asciidoctor-with-spring-rendering-asciidoc-documents-with-spring-mvc/)

You can run the unit tests of this project by running the following command at command prompt:

    mvn clean test -P dev

You can run this application by running the following command at command prompt:

    mvn clean jetty:run -P dev
    
KNOWN ISSUES

* AsciidoctorJ creates an extra stylesheet called asciidoctor.css
