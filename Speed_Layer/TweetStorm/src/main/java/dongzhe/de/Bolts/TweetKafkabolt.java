package dongzhe.de.Bolts;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Status;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class TweetKafkabolt extends BaseRichBolt{

	private static final long serialVersionUID = 2807707049002914239L;
	OutputCollector _collector;
    LinkedBlockingQueue<Status> statusqueue = new LinkedBlockingQueue<Status>();
    private static final Logger logger = LoggerFactory.getLogger(TweetKafkabolt.class);
    
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
    	  //statusqueue.offer(tuple);
    	  String ret = tuple.getString(0);
    	  //kafka bolt receive and emit all tweet
    	  _collector.emit(new Values(ret));
          logger.info("\n\n");
          logger.info(ret);
    }

    @Override
    public void cleanup() {

    }
    //output field
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("tweet"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

}
