x=[0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 ];
deadr=10*[0 0.01 0.03 0.09 0.12 0.18 0.25 0.36 0.52 0.72 0.88 1.27 1.33 1.48 1.67 1.91 2.21 2.63 3.75 4.8 5.97 7.89 9.12 11.23 13.45 16.67 19.76];
ab=10*[0 0.4 0.4 0.4 0.4 0.4 0.4 0.4 0.4 0.3 0.4 0.4 0.3 0.4 0.4 0.4 0.4 0.4 0.4 0.4 0.4 0.4 0.3 0.3 0.4 0.4 0.4 ];
bc=10*[0 0.5 0.5 0.5 0.5 0.5 0.5 0.6 0.6 0.6 0.6 0.6 0.5 0.5 0.6 0.6 0.6 0.6 0.6 0.6 0.5 0.5 0.6 0.6 0.6 0.6 0.6 ];
ac=10*[0 0.5 0.5 0.5 0.5 0.5 0.5 0.6 0.6 0.6 0.6 0.6 0.6 0.6 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 ];
abc=10*[0 0.32 0.31 0.32 0.32 0.31 0.32 0.33 0.32 0.32 0.32 0.31 0.32 0.30 0.30 0.34 0.35 0.35 0.34 0.35 0.34 0.34 0.33 0.33 0.33 0.33 0.32 ];


plot(x,deadr,'--*r');hold on;
plot(x,ab);hold on;
plot(x,bc);hold on;
plot(x,ac);hold on;
plot(x,abc,'--o');hold on;