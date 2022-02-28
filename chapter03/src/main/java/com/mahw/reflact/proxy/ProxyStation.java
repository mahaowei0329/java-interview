package com.mahw.reflact.proxy;

/**
 * 代理类
 */
public class ProxyStation implements Station{

    private BusStation busStation;

    public ProxyStation(BusStation busStation) {
        this.busStation = busStation;
    }

    @Override
    public void saleTicket() {
        System.out.println("Proxy sale station");
        busStation.saleTicket();
    }
}
