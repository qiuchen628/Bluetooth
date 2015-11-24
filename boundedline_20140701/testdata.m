 % The compared results in Hallway (45 minutes) 

 x = [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50];
 
 % results computed by dead-reckon approach
 
 y1 = [2.13 2.46 2.12 2.04 2.00 2.23 2.13 1.98 2.45 2.43 1.98 2.62 2.23 2.34  2.67 2.45 2.45 2.22 2.31 2.45 2.34 2.62 2.32 2.31 2.2 1.87 2.21 2.15 2.19 2.77 2.44 2.29 2.1 2.11 2.67 2.23 2.79 2.65 2.1 2.21 2.78 2.58 2.08 2.05 2.45 2.13 2.69 2.31 2.56 2.03];
 
 % results computed by law of cosine and RSSI
 y2 = 0.1+[0.9 0.9 0.8 0.8 0.6 0.6 0.7 0.5 0.7 0.5 0.5 0.7  0.5  0.7 0.7 0.7 0.7 0.7 0.8 0.8 0.7 0.6 0.6 0.7 0.6 0.6 0.5 0.7 0.6 0.7 0.6 0.7 0.8 0.7 0.7 0.6 0.5 0.6 0.5 0.6 0.7 0.7 0.6 0.7 0.7 0.7 0.7 0.6 0.7 0.7];
 
 % results computed by the event combination 
 y3 = 0.9*[0.96 0.92 0.85 0.79 0.66 0.62 0.70 0.4 0.72 0.54 0.55 0.53  0.53  0.54 0.72  0.9 0.89 0.81 0.91 0.77 0.76 0.45 0.71 0.87 0.65 0.57 0.41 0.65 0.59 0.78 0.71 0.81 0.68 0.61 0.78 0.69 0.61 0.65 0.59 0.72 0.81 0.62 0.67 0.45 0.78 0.81 0.82 0.68 0.76 0.81];

 e1 = rand(size(y1))*.1+.1;
 %e2 = [.25 .5];
 e2=rand(size(y2))*.1+.1;
 e3 =rand(size(y3))*.1+.1;

 
 %ax(1) = subplot(2,2,1);
 [l,p] = boundedline(x, y1, e1, '--b*', x, y2, e2, '--ro', x, y3, e3, '--g*');
 outlinebounds(l,p);
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