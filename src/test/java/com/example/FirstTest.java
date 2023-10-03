package com.example;
import org.testng.Assert;
import org.testng.annotations.Test;

     public class FirstTest {
        @Test
        public void Test_1() {
            System.out.println("FirstTest.Test_1()");
            Assert.assertTrue(true);
        }

        @Test
        public void Test_2() {
            System.out.println("FirstTest.Test_2()");
            Assert.assertFalse(false);
        }
    }

