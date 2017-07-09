package br.pucpr.mage.util;

import java.io.InputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luis
 */
public class ResourceUtil {

    public static InputStream loadStream(String stream) {
        return ResourceUtil.class.getResourceAsStream(stream);
    }
}
