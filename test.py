from bs4 import BeautifulSoup
import os
import csv
import json
b = '{"title":"The A-Team (+ Digital Copy) [Blu-ray]","Is Discontinued By Manufacturer":"No","MPAA rating":"s_medPG13 PG-13 (Parents Strongly Cautioned)","Product Dimensions":"6.75 x 5.3 x 0.45 inches; 4 Ounces","Item model number":"FOXS2270153BR","Director":"Joe Carnahan","Media Format":"Multiple Formats, AC-3, Blu-ray, Color, Dolby, DTS Surround Sound, Dubbed, NTSC, Subtitled, Widescreen, Digital_copy","Run time":"1 hour and 58 minutes","Release date":"December 14, 2010","Actors":"Liam Neeson, Bradley Cooper, Quinton "Rampage" Jackson, Sharlto Copley, Jessica Biel","Dubbed:":"French, Spanish","Subtitles:":"English, Spanish","Studio":"20th Century Fox","ASIN":"B004856SQ8","Number of discs":"2"}'

a = '{"t":"D","I":"N","g":"s","s":"s","r":"6","r":"r","t":"C","e":"s","e":"07","s":"e","o":"o","N":"E","s":"1"}'
a_dict = json.loads(a)