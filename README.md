# *Barian*

---------------

# Overview

* 動的にクラスを持ってくる（予定）。
    * 例えば、[Javassist](http://jboss-javassist.github.io/javassist/)を使えば、クラスをダンプすることができる。  
        * しかし、ダンプされたクラスはロードされた時点でのクラスらしい。  
        * ロード後に書き換えられたクラスがあったとしても、ロードされた時点でのクラスがダンプされてしまう。
    * 何がしたいかというと、実行時にクラスが書き換えられたとしても、そのクラスを持ってくるということがしたい。

---------------

# Usage

* compile
    
    ```
    $ bash compile.sh [file path to aspectjrt.jar]
    or
    $ sh compile.sh [file path to aspectjrt.jar]
    ```

* execute

    ```
    $ java -javaagent:build/libs/Barian.jar=Main.class -cp .:build/libs/*:[file path to aspectjrt.jar] Main
    or 
    $ java -javaagent:build/libs/Barian.jar=Main.class -cp .:build/libs/*:[directory exists aspectjrt.jar]/* Main
    ```

---------------

# Issues

現状、ロード時点でのクラスしか持ってこられていない。

---------------

# References


---------------

# Requirements

* [Java SE 8](http://www.oracle.com/technetwork/java/javase/overview/index.html)
* [Gradle 2.x](https://gradle.org/)
* [AspectJ 1.8.x](https://eclipse.org/aspectj/)

---------------

# Dependencies

* [ASM](http://asm.ow2.org/)
