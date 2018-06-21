package com.hh.blockchain.pow;

/**
 * @author HaoHao
 * @Description: 工作量计算结果
 * @date 2018/6/20下午3:48
 */
public class POWResult {

    private long nonce;

    private String hash;

    public POWResult(long nonce, String hash) {
        this.nonce = nonce;
        this.hash = hash;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
