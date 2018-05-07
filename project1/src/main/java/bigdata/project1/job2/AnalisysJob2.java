package bigdata.project1.job2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class AnalisysJob2 {
	public static void main( String[] args ) throws IOException, ClassNotFoundException, InterruptedException
	{
		Job job = new Job(new Configuration(), "AmazonJob2");

		job.setJarByClass(AnalisysJob2.class);
		job.setMapperClass(MapperJob2.class);
		job.setCombinerClass(CombinerJob2.class);
		job.setReducerClass(ReducerJob2.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapOutputKeyClass(YearProductWritable.class);
		job.setMapOutputValueClass(Avarage.class);

		job.setOutputKeyClass(YearProductWritable.class);
		job.setOutputValueClass(DoubleWritable.class);

		job.waitForCompletion(true);


		Job job2 = new Job(new Configuration(), "AmazonJob2.2");
		job2.setJarByClass(AnalisysJob2.class);
		job2.setMapperClass(MapperPart2.class);
		job2.setReducerClass(ReducerPart2.class);

		FileInputFormat.addInputPath(job2, new Path(args[1]));
		FileOutputFormat.setOutputPath(job2, new Path(args[1] + "/final"));

		job2.setMapOutputKeyClass(Text.class);
		job2.setMapOutputValueClass(Text.class);

		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);

		job2.waitForCompletion(true);

	}
}
