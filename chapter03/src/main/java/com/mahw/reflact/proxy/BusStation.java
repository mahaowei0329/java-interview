package com.mahw.reflact.proxy;

/**
 * 被代理类
 */
public class BusStation implements Station{
    @Override
    public void saleTicket() {
        System.out.println("BusStation Sale Ticket");
    }
}
