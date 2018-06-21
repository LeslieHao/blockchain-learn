package com.hh.blockchain.pow;

import java.time.Instant;

import com.hh.blockchain.block.Block;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author HaoHao
 * @Description:Proof of work
 * @date 2018/6/20下午3:42
 */
public class POW {

    /**
     * 难度目标位
     */
    public static final int DIFFICULT = 5;

    private Block block;


    public POW(Block block) {
        this.block = block;
    }


    /**
     * 开始挖矿
     *
     * @return
     */
    public POWResult mine() {
        long nonce = 0;

        // hash
        String shaHex = "";
        System.out.println("开始挖矿==>data:" + this.getBlock().getData());
        // 前几位是0
        String target = new String(new char[DIFFICULT]).replace('\0', '0');
        //开始时间
        long startTime = System.currentTimeMillis();
        while (StringUtils.isEmpty(shaHex) || !shaHex.substring(0, DIFFICULT).equals(target)) {
            // 挖矿一次
            nonce++;
            block.setNonce(nonce);
            block.setTimeStamp(Instant.now().getEpochSecond());
            // sha256 算法获取hash 值
            shaHex =block.calculateHash();
        }

        System.out.println("耗时: " + (float) (System.currentTimeMillis() - startTime) / 1000);
        System.out.println("hash: " + shaHex);
        return new POWResult(nonce, shaHex);
    }


    public static POW newPOW(Block block) {
        return new POW(block);
    }

    public Block getBlock() {
        return block;
    }


}
