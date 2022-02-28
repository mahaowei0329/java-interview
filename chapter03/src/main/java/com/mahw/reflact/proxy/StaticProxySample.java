package com.mahw.reflact.proxy;

public class StaticProxySample {

    public static void main(String[] args) {
        BusStation busStation = new BusStation();
        ProxyStation proxyStation = new ProxyStation(busStation);
        proxyStation.saleTicket();
    }
}
