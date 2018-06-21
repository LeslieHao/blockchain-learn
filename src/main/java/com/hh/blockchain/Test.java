package com.hh.blockchain;


import java.util.ArrayList;
import java.util.List;

import com.hh.blockchain.block.Block;
import com.hh.blockchain.block.BlockChain;

/**
 * @author HaoHao
 * @Description:测试类
 * @date 2018/6/20下午4:12
 */
public class Test {

    private static void testPOW() {
        BlockChain blockChain = BlockChain.newBlockChain();
        blockChain.addBlock("second block~");
        blockChain.addBlock("third block~");
        System.out.println("");
        System.out.println("区块链是否合法: "+blockChain.isChainValid());
    }


    private static BlockChain initChain() throws InterruptedException {
        List<Block> chain = new ArrayList<>();
        BlockChain blockChain = new BlockChain(chain);
        Block block1 = new Block("", "block 1");
        chain.add(block1);
        Thread.sleep(1000);
        Block block2 = new Block(chain.get(chain.size() - 1).getHash(), "block 2");
        chain.add(block2);
        Thread.sleep(1000);
        Block block3 = new Block(chain.get(chain.size() - 1).getHash(), "block 3");
        chain.add(block3);
        return blockChain;
    }

    public static void main(String[] args) throws InterruptedException {
        testPOW();
//        BlockChain blockChain = initChain();
//        System.out.println("block chain 是否合法: " + blockChain.isChainValid());
//        blockChain.getChain().get(1).setData("change");
//        System.out.println("block chain 是否合法: " + blockChain.isChainValid());
    }

}
