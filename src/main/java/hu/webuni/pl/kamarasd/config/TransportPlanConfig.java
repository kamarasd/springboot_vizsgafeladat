package hu.webuni.pl.kamarasd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "transportplan")
public class TransportPlanConfig {
	
	private Decrase decrase = new Decrase();
	
	public Decrase getDecrase() {
		return decrase;
	}

	public void setDecrase(Decrase decrase) {
		this.decrase = decrase;
	}

	public static class Decrase {
		
		private int thirtyMin;
		private int sixtyMin;
		private int hundredAndTwentyMin;
		
		private int fivep;
		private int tenp;
		private int fifteenp;
		
		public int getFivep() {
			return fivep;
		}

		public void setFivep(int fivep) {
			this.fivep = fivep;
		}

		public int getTenp() {
			return tenp;
		}

		public void setTenp(int tenp) {
			this.tenp = tenp;
		}

		public int getFifteenp() {
			return fifteenp;
		}

		public void setFifteenp(int fifteenp) {
			this.fifteenp = fifteenp;
		}
		
		public int getThirtyMin() {
			return thirtyMin;
		}
		
		public void setThirtyMin(int thirtyMin) {
			this.thirtyMin = thirtyMin;
		}
		
		public int getSixtyMin() {
			return sixtyMin;
		}
		
		public void setSixtyMin(int sixtyMin) {
			this.sixtyMin = sixtyMin;
		}
		
		public int getHundredAndTwentyMin() {
			return hundredAndTwentyMin;
		}
		
		public void setHundredAndTwentyMin(int hundredAndTwentyMin) {
			this.hundredAndTwentyMin = hundredAndTwentyMin;
		}
	}

}
