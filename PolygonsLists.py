#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Mar 23 13:42:15 2021

@author: 4472245
"""

#---- the script generates the list of all polygons of the sample, the list of all inner (aka necrosis) polygons of the sample
# and the stroma polygon list
import os,json
#import matplotlib.pyplot as plt
import csv
from math import floor, trunc, ceil
from shapely.geometry import Polygon
from shapely.geometry import Point

scalefactor = 0.5022
#-- verify if the list of xy coordinates for the polygon vertices exists already ----

if os.path.isfile('PolygonsMainSmooth.txt'):
    with open('PolygonsMainSmooth.txt', 'r') as f_f:
        PolyList = json.loads(f_f.read())
else:
    PolyList = []
    
print("poly list", PolyList)
    
if os.path.isfile('innerPolyList.txt'):
    with open('innerPolyList.txt', 'r') as f_f1:
        innerPolyList = json.loads(f_f1.read())
else:
    innerPolyList = []    
    
print("inner polygons", len(innerPolyList))
# if os.path.isfile('StromaPolyList.txt'):
#     with open('StromaPolyList.txt', 'r') as f_f2:
#         PolyListS = json.loads(f_f2.read())
# else:
#     PolyListS = []    

if len(PolyList)==0:   
    myListx = []
    myListy = []
    myL1 = []
    myL2 = []

    with open('PolygonsMainSmooth.csv', newline='') as csvfile:
       reader = csv.DictReader(csvfile)
       for row in reader:
           myListx.append(ceil(float(row['X'])))
           myListy.append(trunc(float(row['Y'])))
           myL1.append(floor(float(row['L1'])))
           myL2.append(ceil(float(row['L2'])))

    AllList = []
    keyListTemp = []
    KeyList = []
    PolyList = []
    for i in range(len(myListx)):
        AllList.append([(myListx[i]*scalefactor,myListy[i]*scalefactor), (myL1[i],myL2[i])])
     
    for j in range(len(myListx)):
        keyListTemp.append(AllList[j][1])
        
    setList = set(keyListTemp)
    KeyList = list(setList)
    #print(KeyList, len(KeyList))
        
    PolyDict = {}    
    for value in KeyList:
        tempList = []
        for entr in AllList:
            if entr[1] == value:
                tempList.append(entr[0])
        PolyDict[value] = tempList
    
    for key in PolyDict:
        PolyList.append(PolyDict[key])
        
    if os.path.isfile('PolyListTissue.txt'):
          with open('PolyListTissue.txt', 'w') as f_f:
              f_f.write(json.dumps(PolyList))
    else:
        with open('PolyListTissue.txt', 'w') as f_f:
              f_f.write(json.dumps(PolyList))
              
#--- getting the list of the inner Polygons ----
              
if len(innerPolyList) == 0:
    innerPolyList = []
    for f in PolyList:
        pt = Point(f[0])
        for d in PolyList:
            if d != f:
                polyg = Polygon(d)
                if polyg.contains(pt):
                    innerPolyList.append(f)
    
    if os.path.isfile('innerPolyList.txt'):
          with open('innerPolyList.txt', 'w') as f_f1:
              f_f1.write(json.dumps(innerPolyList))
    else:
        with open('innerPolyList.txt', 'w') as f_f1:
              f_f1.write(json.dumps(innerPolyList))

# # -- generating stroma polygons --
# if len(PolyListS) == 0:
#     myListxS = []
#     myListyS = []
#     myL1S = []
#     myL2S = []

#     with open('StromaPolygons.csv', newline='') as csvfile:
#        reader = csv.DictReader(csvfile)
#        for row in reader:
#            myListxS.append(int(row['X']))
#            myListyS.append(int(row['Y']))
#            myL1S.append(int(row['L1']))
#            myL2S.append(int(row['L2']))

#     AllListS = []
#     keyListTempS = []
#     KeyListS = []
#     PolyListS = []
#     for i in range(len(myListxS)):
#         AllListS.append([(myListxS[i]*scalefactor,myListyS[i]*scalefactor), (myL1S[i],myL2S[i])])
     
#     for j in range(len(myListxS)):
#         keyListTempS.append(AllListS[j][1])
        
#     setListS = set(keyListTempS)
#     KeyListS = list(setListS)
#     #print(KeyList, len(KeyList))
        
#     PolyDictS = {}    
#     for value in KeyListS:
#         tempListS = []
#         for entr in AllListS:
#             if entr[1] == value:
#                 tempListS.append(entr[0])
#         PolyDictS[value] = tempListS
    
#     for key in PolyDictS:
#         PolyListS.append(PolyDictS[key])
        
#     if os.path.isfile('StromaPolyList.txt'):
#           with open('StromaPolyList.txt', 'w') as f_f2:
#               f_f2.write(json.dumps(PolyListS))
#     else:
#         with open('StromaPolyList.txt', 'w') as f_f2:
#               f_f2.write(json.dumps(PolyListS))

              

# for i in PolyList:
#     polygon1 = Polygon(i)
#     x,y = polygon1.exterior.xy
#     plt.plot(x,y, "k")
# plt.savefig("polygonsandStroma.png")     
       
              