package com.lhm.study.code.购物单;

import java.util.*;

/**
 * PS:复杂度过高，待优化
 */
public class Main {

    public static final String SPACE = " ";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s1 = input.nextLine();
        String[] split = s1.split(SPACE);
        int money = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        Map<Integer, Shop> shops = new HashMap<>(m);
        ArrayList<Shop> subShops = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            String line = input.nextLine();
            String[] arr = line.split(SPACE);
            int v = Integer.parseInt(arr[0]);
            int p = Integer.parseInt(arr[1]);
            int q = Integer.parseInt(arr[2]);
            Shop shop = new Shop(v, p, q);
            if (q == 0) {
                shops.put(i + 1, shop);
            } else {
                subShops.add(shop);
            }
        }
        for (Shop subShop : subShops) {
            Shop shop = shops.get(subShop.q);
            if (shop != null) {
                shop.addSubShop(subShop);
            }
        }
        System.out.println(System.currentTimeMillis());
        func(new ArrayList<>(shops.values()), 0, money, 0);
        System.out.println(System.currentTimeMillis());
        System.out.println(result);
    }

    private static int result = 0;

    private static void func(ArrayList<Shop> shops, int index, int money, int love) {
        if (shops.size() == index) {
            // 证明所有商品都满足 记录love
            result = Math.max(result, love);
            return;
        }
        for (int i = index; i < shops.size(); i++) {
            Shop shop = shops.get(i);
            int sMoney = money - shop.v;
            if (sMoney < 0) { // 余额不够
                // 记录 love
                result = Math.max(result, love);
                continue;
            }
            int loveTemp = love + shop.getLove();
            if (shop.subShop.size() == 0) {
                func(shops, i + 1, sMoney, loveTemp);
                continue;
            }
            Package subShop1 = shop.getSubShop1();
            if (subShop1 != null) {
                sMoney = money - subShop1.price;
                if (sMoney < 0) { // 余额不够
                    // 记录 love
                    result = Math.max(result, love);
                } else {
                    func(shops, i + 1, sMoney, love + subShop1.love);
                }
            }
            Package subShop2 = shop.getSubShop2();
            if (subShop2 != null) {
                sMoney = money - subShop2.price;
                if (sMoney < 0) { // 余额不够
                    // 记录 love
                    result = Math.max(result, love);
                } else {
                    func(shops, i + 1, sMoney, love + subShop2.love);
                }
            }
            Package subShopAll = shop.getSubShopAll();
            if (subShopAll != null) {
                sMoney = money - subShopAll.price;
                if (sMoney < 0) { // 余额不够
                    // 记录 love
                    result = Math.max(result, love);
                } else {
                    func(shops, i + 1, sMoney, love + subShopAll.love);
                }
            }
        }
    }

    private static class Package {
        int price;
        int love;
    }

    private static class Shop {
        int v;
        int p;
        int q;
        private final List<Shop> subShop = new ArrayList<>(2);

        private Package getSubShop1() {
            if (subShop.size() <= 0) {
                return null;
            }
            Package pack = new Package();
            Shop sub = subShop.get(0);
            pack.price = v + sub.v;
            pack.love = getLove() + sub.getLove();
            return pack;
        }

        private Package getSubShop2() {
            if (subShop.size() <= 1) {
                return null;
            }
            Package pack = new Package();
            Shop sub = subShop.get(1);
            pack.price = v + sub.v;
            pack.love = getLove() + sub.getLove();
            return pack;
        }

        private Package getSubShopAll() {
            if (subShop.size() <= 1) {
                return null;
            }
            Package pack = new Package();
            Shop sub1 = subShop.get(0);
            Shop sub2 = subShop.get(1);
            pack.price = v + sub1.v + sub2.v;
            pack.love = getLove() + sub1.getLove() + sub2.getLove();
            return pack;
        }

        public Shop(int v, int p, int q) {
            this.v = v;
            this.p = p;
            this.q = q;
        }

        public void addSubShop(Shop shop) {
            subShop.add(shop);
        }

        public int getLove() {
            return v * p;
        }
    }
}
