package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealershipFileManagerTest {

    @Test
    void getDealership_fromFile_returnsDealer() {
        //arrange
        DealershipFileManager fileManager = new DealershipFileManager();

        //act
        Dealership dealership = fileManager.getDealership();

        //assert
        assertEquals("D & B Used Cars", dealership.getName());
        assertEquals("111 Old Benbrook Rd", dealership.getAddress());
        assertEquals("817-555-5555", dealership.getPhone());

    }
}