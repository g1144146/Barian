# *Barian*

---------------

# Overview

* 動的にクラスを持ってくる（予定）  
    * 例えば、[Javassist](http://jboss-javassist.github.io/javassist/)を使えば、クラスをダンプすることができる
        * しかし、ダンプされたクラスはロードされた時点でのクラスらしい
        * ロード後に書き換えられたクラスがあったとしても、ロードされた時点でのクラスがダンプされてしまう
    * 何がしたいかというと、実行時にクラスが書き換えられたとしても、そのクラスを持ってくるということがしたい

6/30 追記  
* バイトコード書き換え後のクラスを持ってくることはできた
    * `Class.forName(String)`や`ClassLoader`の一部のメソッドで確認した
        * `ClassLoader`に関しては一部しか調べていない
    * メソッドの返り値を変更して再定義前後の結果を確認すると、変更が適用されていた
    * 持ってきたClass型オブジェクトから、オペコードを変更したメソッドのMethod型オブジェクトを取り出してハッシュ値を比較したところ、再定義前後で変化はなかった

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
    $ bash exec.sh [file path to aspectjrt.jar]
    or 
    $ bash exec.sh [directory exists aspectjrt.jar]
    ```

---------------

# Issues

クラスファイルにどう落とし込むか・・・。

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
* [AspectJ 1.8.x](https://eclipse.org/aspectj/)

