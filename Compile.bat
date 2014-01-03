@echo off
javac MONIP2.java
java MONIP2 < d0inD.txt > myd0D_01.out
java MONIP2 < d0inL.txt > myd0L_01.out
java MONIP2 < d0inQ.txt > myd0Q_01.out
java MONIP2 < d1inD.txt > myd1D_01.out
java MONIP2 < d1inL.txt > myd1L_01.out
java MONIP2 < d1inQ.txt > myd1Q_01.out
java MONIP2 < d2inD.txt > myd2D_01.out
java MONIP2 < d2inL.txt > myd2L_01.out
java MONIP2 < d2inQ.txt > myd2Q_01.out

fc d0outD.txt myd0D_01.out > errors/errors_d0D_01.txt
fc d0outL.txt myd0L_01.out > errors/errors_d0L_01.txt
fc d0outQ.txt myd0Q_01.out > errors/errors_d0Q_01.txt
fc d1outD.txt myd1D_01.out > errors/errors_d1D_01.txt
fc d1outL.txt myd1L_01.out > errors/errors_d1L_01.txt
fc d1outQ.txt myd1Q_01.out > errors/errors_d1Q_01.txt
fc d2outD.txt myd2D_01.out > errors/errors_d2D_01.txt
fc d2outL.txt myd2L_01.out > errors/errors_d2L_01.txt
fc d2outQ.txt myd2Q_01.out > errors/errors_d2Q_01.txt
