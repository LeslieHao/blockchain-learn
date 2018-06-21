package com.hh.blockchain.block;

import java.time.Instant;
import java.util.Date;

import com.hh.blockchain.pow.POW;
import com.hh.blockchain.pow.POWResult;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author HaoHao
 * @Description: 区块
 * @date 2018/6/20下午2:42
 */
public class Block {

    // 当前区块hash
    private String hash;

    // 前区块hash
    private String preHash;

    // 区块数据(交易数据)
    private String data;

    // 区块创建时间
    private long timeStamp;

    // 工作量证明
    private long nonce;


    public Block(String hash, String preHash, String data) {
        this.hash = hash;
        this.preHash = preHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.nonce = 0;
    }

    public Block(String preHash, String data) {
        this.preHash = preHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.nonce = 0;
        this.hash = calculateHash();
    }

    /**
     * 头结点
     *
     * @return
     */
    public static Block newGenesisBlock() {
        return Block.newBlock("", "first Block");
    }

    /**
     * 创建新区块
     *
     * @param preHash
     * @param data
     * @return
     */
    public static Block newBlock(String preHash, String data) {
        Block block = new Block("", preHash, data);
        // 工作量证明(挖矿)
        POW pow = POW.newPOW(block);
        POWResult result = pow.mine();
        System.out.println("nonce: " + result.getNonce());
        block.setHash(result.getHash());
        block.setNonce(result.getNonce());
        return block;
    }


    /**
     * 计算hash
     *
     * @return
     */
    public String calculateHash() {
        String data = this.getPreHash()
                + Long.toString(this.getTimeStamp())
                + Long.toString(nonce)
                + this.getData();
        return DigestUtils.sha256Hex(data);
    }


    public String getHash() {
        return hash;
    }

    public String getPreHash() {
        return preHash;
    }

    public void setPreHash(String preHash) {
        this.preHash = preHash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
