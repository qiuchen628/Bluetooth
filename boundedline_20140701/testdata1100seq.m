% The compared results in EB1100 room (45 minutes) 

 x = [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45];
 
 % results computed by dead-reckon approach
 
 y1 = [0.15 0.28 3.32 4.13 3.87 0.76 1.36 6.21 5.31 3.45 4.54 5.11 5.5 5.23 4.32 4.23 4.01 3.21 2.76 2.87 2.45 4.78 6.89 6.1 6.34 2.34 1.01 1.3 0.52 1.34 2.34 3.58 4.12 4.03 0.2 3.04 0.23 0.34 2.45 3.21 5.21 0.22 5.61 6.32 3.21];
 
 % results computed by law of cosine and RSSI
 y2 = 1.2*[0.4 0.5 0.5 0.4 0.5 0.4 0.5 0.6 0.8 0.6 0.4 0.6 0.7 0.7 0.5 0.5 0.5 0.6 0.6 0.7 0.8 0.6 0.5 0.5 0.5 0.5 0.6 0.6 0.5 0.3 0.5 0.5 0.3 0.5 0.4 0.4 0.5 0.6 0.6 0.7 0.6 0.6 0.5 0.6 0.5];
 
 % results computed by the event combination 
 y3 = 0.96*[0.15 0.28 0.59 0.52 0.43 0.60 0.53 0.63 0.91 0.23 0.34 0.6 0.84 0.64 0.22 0.53 0.34 0.64 0.71 0.81 0.92 0.69 0.45 0.57 0.58 0.62 0.68 0.48 0.65 0.39 0.46 0.57 0.35 0.56 0.46 0.45 0.57 0.52 0.74 0.81 0.65 0.57 0.56 0.2 0.51];

 %er1 = rand(size(y1))*.5+.5;
 %e2 = [.25 .5];
 %er2=rand(size(y2))*.5+.5;
 %er3 =rand(size(y3))*.5+.5;

 plot(x,y1,'b');hold on;plot(x,y2,'r');hold on;plot(x,y3,'g');hold on;
 
 
 
 %ax(1) = subplot(2,2,1);
 %[l,p] = boundedline(x, y1, er1, '--b*', x, y2, er2, '--ro', x, y3, er3, '--g*');
 %outlinebounds(l,p);
 %title('Opaque bounds, with outline');
 
 %ax(2) = subplot(2,2,2);
 %boundedline(x, [y1;y2], rand(length(y1),2,2)*.5+.5, 'alpha');
 %title('Transparent bounds');
 
 %ax(3) = subplot(2,2,3);
 %boundedline([y1;y2], x, e1(1), 'orientation', 'horiz')
 %title('Horizontal bounds');
 
 %ax(4) = subplot(2,2,4);
 %boundedline(x, repmat(y1, 4,1), permute(0.5:-0.1:0.2, [3 1 2]), ...
 %            'cmap', cool(4), 'transparency', 0.5);
 %title('Multiple bounds using colormap');