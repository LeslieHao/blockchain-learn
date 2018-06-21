package com.hh.blockchain.block;

import java.util.LinkedList;
import java.util.List;

import com.hh.blockchain.pow.POW;

/**
 * @author HaoHao
 * @Description:
 * @date 2018/6/20下午3:06
 */
public class BlockChain {

    public List<Block> getChain() {
        return chain;
    }

    public void setChain(List<Block> chain) {
        this.chain = chain;
    }

    private List<Block> chain;

    public BlockChain(List<Block> chain) {
        this.chain = chain;
    }


    /**
     * 创建区块链
     * @return
     */
    public static BlockChain newBlockChain(){
        List<Block> blocks = new LinkedList<>();
        blocks.add(Block.newGenesisBlock());
        return new BlockChain(blocks);
    }

    /**
     * 添加区块
     * @param data
     */
    public void addBlock(String data) {
        Block preBlock = chain.get(chain.size() - 1);
        this.addBlock(Block.newBlock(preBlock.getHash(), data));
    }

    /**
     * 添加区块
     * @param block
     */
    public void addBlock(Block block){
        this.chain.add(block);
    }

    /**
     * 合法性校验
     * @return
     */
    public boolean isChainValid() {

        System.out.println("开始校验区块链合法性~");

        Block cur;

        Block pre = null;

        String hashTarget = new String(new char[POW.DIFFICULT]).replace("\0", "0");

        for (int i = 0; i < chain.size(); i++) {
            cur = chain.get(i);
            if (i > 0) {
                pre = chain.get(i - 1);
            }
            // 比较当前块注册块hash 和 计算出的块hash
            if (!cur.getHash().equals(cur.calculateHash())) {
//                System.out.println("区块链发生串改");
                return false;
            }

            // 比较上个块的注册hash 和 前块的计算hash
            if (pre != null && !cur.getPreHash().equals(pre.calculateHash())) {
//                System.out.println("区块链发生串改");
                return false;
            }

//            // 检查hash 是否合法
            if (!cur.getHash().substring(0, POW.DIFFICULT).equals(hashTarget)) {
                System.out.println("这个区块hash 不合法");
                return false;
            }

        }
        return true;
    }



}
