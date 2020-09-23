import pandas as pd
import requests
from bs4 import BeautifulSoup
import numpy as np
import os

def stockDataCheck():
    path = "./"
    file_list = os.listdir(path)
    file_list_xlsx = [file for file in file_list if file.endswith("stockdata.xlsx")]

   
    if file_list_xlsx:
        df = pd.read_excel('stockdata.xlsx')
        df.code = df.code.map('{:06d}'.format) #6자리로 맞추기
        
    if not file_list_xlsx:
        df = pd.read_html('http://kind.krx.co.kr/corpgeneral/corpList.do?method=download&searchType=13',header=0)[0]
        df = df.rename(columns={'회사명':'name','종목코드':'code'})
        df.code = df.code.map('{:06d}'.format) #6자리로 맞추기
        df = df[['name','code']]
        df.to_excel('stockdata.xlsx')
    return df

def stockCodeSearch(stockname):
    
    df = stockDataCheck()   
    stockdata = df.query('name == ["'+stockname+'"]').head()
    
    stockcode = str(stockdata.code.values)[2:8] #코드만 슬라이싱

    df3 = stockTemp(stockcode)
    html = df3.to_html()
    
    return html

def stockTemp(stockcode):
    URL = "https://finance.naver.com/item/main.nhn?code={stockcode}".format(stockcode=stockcode)
    html = requests.get(URL).text
    soup = BeautifulSoup(html, 'html.parser')
    finance_html = soup.select('div.section.cop_analysis div.sub_section')[0]
    #긁어온 html 코드에서 thread th 부분만 선택하고, 양쪽 끝의 공백과 \n 부분은 삭제한 텍스트 데이터를 추출한다.
    th_data = [item.get_text().strip() for item in finance_html.select('thead th')]
    annual_date = th_data[3:7]  #잘라온 재무제표의 데이터중 왼쪽의 연간 날짜 부분. ex)['2017.06', '2018.06', '2018.12', '2019.12(E)']
    quarter_date = th_data[7:13] # 그 데이터의 오른쪽 분기별 날짜부분 ex)['2018.03', '2018.06', '2018.09', '2018.12', '2019.03', '2019.06(E)']

    #재무제표의 행 index
    finance_index = [item.get_text().strip() for item in finance_html.select('th.h_th2')][3:]
    #인덱스를 제외한 실질적인 수치들
    finance_data = [item.get_text().strip() for item in finance_html.select('td')]



    finance_data = np.array(finance_data) #데이터를 numpy 배열형태로 변환시킴
    finance_data.resize(len(finance_index), 10) #10개로 정렬시키도록... 재무제표 형태로 리사이징

    finance_date = annual_date + quarter_date #날짜부분을 붙여줌

    #데이터프레임에 인덱스와 열 데이터를 합산한다
    finance = pd.DataFrame(data=finance_data[0:,0:], index=finance_index, columns=finance_date)

    #합산 된 왼쪽 연간 재무제표 데이터
    annual_finance = finance.iloc[:, :4] 
    #오른쪽 분기별 재무제표 데이터
    #quarter_finance = finance.iloc[:, 4:]


    #exchangeList > li.on > a.head.usd > div > span.change
    #원하는 데이터 추출하기

    #annual_finance["2018.12"]
    #18년 12월 데이터만 잘라주고, eps 증가율 계산.
    #결산 연월이 다른 데이터같은 경우 일단 넘어감.

    #/////////////////////////////////////////////////////////
    #아래의 처리를 한 이유는 작년 재무제표 데이터와 제작년 재무제표 데이터만 잘라내서 eps 데이터를 구하는데, 종목마다 
    #결산 분기가 12월인 종목이 있고 1월, 3월 이런 종목들이 있어서 에러가 발생한다.
    #그래서 결산 분기가 12월이 아닌 종목들은 걸러내는 작업.
    #이 부분은 차후 수정해야 더 많은 데이터를 구할 수 있다. 
    #날짜 같은 경우 매개변수로 입력 받는 형식으로 수정해야 함
    #////////////////////////////////////////////////////////
    #작년 재무제표 데이터 부분만 잘라냄
    datetemp = finance.iloc[:, 2:3]
    temp = str(datetemp.columns) #형변환을 해서 데이터를 잘라내기 쉽게 변환
    colname1 = temp[8:-19] #결산 년도의 열 인덱스만 잘라내는 부분. 작년 인덱스ex)2018.12

    datetemp = finance.iloc[:,1:2]
    temp = str(datetemp.columns)
    colname2 = temp[8:-19]  #제작년 인덱스

    return annual_finance