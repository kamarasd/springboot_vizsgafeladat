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
		
		private int lvl1;
		private int lvl2;
		private int lvl3;
		
		public int getLvl1() {
			return lvl1;
		}

		public void setLvl1(int lvl1) {
			this.lvl1 = lvl1;
		}

		public int getLvl2() {
			return lvl2;
		}

		public void setLvl2(int lvl2) {
			this.lvl2 = lvl2;
		}

		public int getLvl3() {
			return lvl3;
		}

		public void setLvl3(int lvl3) {
			this.lvl3 = lvl3;
		}

		private Percent percent = new Percent();
		
		public Percent getPercent() {
			return percent;
		}

		public void setPercent(Percent percent) {
			this.percent = percent;
		}
	}
	
	public static class Percent {
		private int lvl1;
		private int lvl2;
		private int lvl3;
		
		public int getLvl1() {
			return lvl1;
		}
		
		public void setLvl1(int lvl1) {
			this.lvl1 = lvl1;
		}
		
		public int getLvl2() {
			return lvl2;
		}
		
		public void setLvl2(int lvl2) {
			this.lvl2 = lvl2;
		}
		
		public int getLvl3() {
			return lvl3;
		}
		
		public void setLvl3(int lvl3) {
			this.lvl3 = lvl3;
		}
	}

}
