# Compiler et exécuter ce programme avec Windows en ligne de commande

```
cd projetJavaSwing/src
javac -cp ".;lib/*" -encoding utf8 .\vue\MainJFrame.java -d "..\out"
cd ..\out
java -cp ".;../src/lib/*" vue.MainJFrame
```
