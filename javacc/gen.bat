@echo off
del prover.jj.jj
del *.java
call jjtree prover.jj
call javacc prover.jj.jj
pause