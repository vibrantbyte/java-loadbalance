package github.vibrant;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Description:</b><br> : 权重轮询
 *
 * @author : suchao@renrenche.com
 * @version : 1.0
 * @Note <b>ProjectName:</b> java-loadbalance
 * <br><b>PackageName:</b> github.vibrant
 * <br><b>Date:</b> 2019/5/24 下午5:08
 */
public class WeightedRoundRobin {

    public static void main(String[] args) {
        List<RoundRobinPeer> data = new ArrayList<>();

        RoundRobinPeer peer1 = new RoundRobinPeer(1L,"a",5);
        data.add(peer1);
        RoundRobinPeer peer2 = new RoundRobinPeer(2L,"b",3);
        data.add(peer2);
        RoundRobinPeer peer3 = new RoundRobinPeer(3L,"c",1);
        data.add(peer3);


        //3、开始进行平衡权益算法调度
        for (int i = 0; i< 9 ;i++ ) {
            RoundRobinPeer peer = getPeer(data);
            if (peer != null) {
                System.out.println(peer.toString());
                System.out.println(String.format("此时的值为：{a:%d,b:%d,c:%d}",peer1.getCurrentWeight(),peer2.getCurrentWeight(),peer3.getCurrentWeight()));

            }
        }

    }

    public static RoundRobinPeer getPeer(List<RoundRobinPeer> data){
        RoundRobinPeer best = null;
        Integer total = 0;
        if (null != data){
            for (RoundRobinPeer peer : data){
                if (peer.getDown()){
                    continue;
                }

                peer.setCurrentWeight(peer.getCurrentWeight() + peer.getEffectiveWeight());
                total += peer.getEffectiveWeight();

                if (peer.getEffectiveWeight() < peer.getWeight()){
                    peer.setEffectiveWeight(peer.getEffectiveWeight() + 1);
                }

                // 若当前后端服务器的权重 current_weight 大于目前 best 服务器的权重，则当前后端服务器被选中
                if (best == null || peer.getCurrentWeight() > best.getCurrentWeight()) {
                    best = peer;
                }

            }

            if (best == null){
                return null;
            }

            best.setCurrentWeight(best.getCurrentWeight() - total);

        }
        return best;
    }


}
