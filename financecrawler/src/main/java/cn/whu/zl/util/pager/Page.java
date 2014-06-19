/**
 * 
 */
package cn.whu.zl.util.pager;

/**
 * @author B506-13-1
 *
 */
public class Page {
		private int current = 0;  //当前页，默认为第一页
		private int size;     //记录总数
		private int length;   //每页的长度
		private String url;   //访问路径
		
		public Page(int offset, int size, int length) {
			super();
			this.current = offset;
			this.size = size;
			this.length = length;
		}
		
		/**
		 * 获取总页数
		 * @return
		 */
		public int pageCount(){
			int pagecount = 0;
			if(this.size % this.length == 0){
				pagecount = this.size / this.length;
			}else{
				pagecount = this.size / this.length + 1;
			}
			
			return pagecount;
		}
		
		//最后一页开始条数
		public int lastPageOffset(){
			return this.size - lastPageSize();
		}
		
		//最后一页页大小
		public int lastPageSize(){
			int lastpagesize = 0;
			if(this.size % this.length == 0){
				lastpagesize = this.length;
			}else{
				lastpagesize = this.size % this.length;
			}
			return lastpagesize;
		}
		
		//获取起始页
		public int getOffset() {
			return current;
		}
		//总记录数
		public int getSize() {
			return size;
		}
		//每页大小
		public int getLength() {
			return length;
		}
		//获取访问路径
		public String getUrl() {
			return url;
		}
		
		//上一页
		public int getLastOffset(){
			int offset = this.getOffset() - this.getLength();
			if(offset > 0){
				return offset;
			}else{
				return 0;
			}
			
		}
		//下一页
		public int getNextOffset(){
			int offset =  this.getOffset() + this.getLength();
			if(offset > this.getSize()){
				return getLastOffset();
			}else{
				return offset;
			}
		}
		
		public String getPageNavigation(){
			String pageNavigation = "";
			return pageNavigation;
		}

		public void setOffset(int offset) {
			this.current = offset;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public void setLength(int length) {
			this.length = length;
		}

		public void setUrl(String url) {
			this.url = url;
		}
}
