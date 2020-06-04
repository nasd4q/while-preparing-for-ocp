/*
    java -p myJars/accounting.jar:myJars/reporting.jar -m reporting/com.abc.reporting.Reporter
        will run correctly even when the 2 jars are regular non modular jars
 */

package com.abc.reporting;

import com.abc.accounts.BasicAccount;

public class Reporter {
    public static void main(String[] args) {
        BasicAccount bAOne = new BasicAccount();
        BasicAccount bATwo = new BasicAccount();

        System.out.println(bAOne);
        System.out.println(bATwo);
    }
}
