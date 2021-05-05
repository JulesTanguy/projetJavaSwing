# Compiler et exécuter ce programme

## Windows
```
cd projetJavaSwing
javac -cp ".;lib/*" -encoding utf8 .\vue\MainJFrame.java -d out
cd out
java -cp ".;../lib/*" vue.MainJFrame
```

## Linux
```
cd projetJavaSwing
javac -cp .:lib/* vue/MainJFrame.java -d out
cd out
java -cp .:../lib/* vue.MainJFrame
```
