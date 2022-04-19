/*


 */

public class ColorRectangle extends Color implements Comparable {
	private int iX1=0;
	private int iX2=0;
	private int iY1=0;
	private int iY2=0;
	public ColorRectangle() {
		super();
	}
	public ColorRectangle(int x1, int y1, int x2, int y2, long c) {
		super(c);
		iX1=x1<x2?x1:x2;
		iY1=y1<y2?y1:y2;
		iX2 = x1<x2?x2:x1; 
		iY2 = y1<y2?y2:y1;
	}
	public int getiX1() {
		return iX1;
	}
	public void setiX1(int iX1) {
		this.iX1 = iX1;
	}
	public int getiX2() {
		return iX2;
	}
	public void setiX2(int iX2) {
		this.iX2 = iX2;
	}
	public int getiY1() {
		return iY1;
	}
	public void setiY1(int iY1) {
		this.iY1 = iY1;
	}
	public int getiY2() {
		return iY2;
	}
	public void setiY2(int iY2) {
		this.iY2 = iY2;
	}
	public int calcArea() {
		return Math.abs((iX2-iX1)*(iY2-iY1));
	}
	public double calcPerimeter(){	
		return 2*(Math.abs(iX1-iX2)+Math.abs(iY1-iY2));
	}
	public int compareTo(Object r) {
		if(this.calcArea()< ((ColorRectangle)r).calcArea()) return -1;
		if(this.calcArea()> ((ColorRectangle)r).calcArea()) return 1;
		return 0;
	}
	@Override
	public String toString() {
		return " [iX1="+iX1 + ", iX2="+iX2+", iY1="+iY1+", iY2="+iY2 +"]"+" Color " + super.toString();	// [iX1=12,..
	}
	public boolean equals(ColorRectangle r) {
		//return (this.calcArea()==r.calcArea());
		return ((this.compareTo(r)==0)?true:false)&& (super.equals(r));
	}
	public void translateX(int iPoints) {
		iX1 += iPoints;
		iX2 += iPoints;
	}
	public void translateY(int iPoints) {
		iY1 += iPoints;
		iY2 += iPoints;
		
	}
	public void translateXY(int iPoints) {
		translateX(iPoints);
		translateY(iPoints);
	}
	public boolean isInside(int ptX, int ptY ) {
		return (ptX>=iX1)&&(ptX<=iX2)&&(ptY>=iY1)&&(ptY<=iY2);
	}
	public ColorRectangle unionRect(ColorRectangle r) {
		return new ColorRectangle(
				(this.iX1 < r.iX1) ? this.iX1 : r.iX1,			//iX1
						(this.iY1 < r.iY1) ? this.iY1 : r.iY1,	//iY1
						(this.iX2 > r.iX2) ? this.iX2 : r.iX2,	//iX2
						(this.iY2 > r.iY2) ? this.iY2 : r.iY2,
						(new Color(super.getColor() | r.getColor())).getColor()		);	//iY2					
	}
	public ColorRectangle intersectionRect( ColorRectangle r) {
		ColorRectangle result =  new ColorRectangle(
				(this.iX1 > r.iX1) ? this.iX1 : r.iX1,		//iX1
				(this.iY1 > r.iY1) ? this.iY1 : r.iY1,		//iY1
				(this.iX2 < r.iX2) ? this.iX2 : r.iX2,		//iX2
				(this.iY2 < r.iY2) ? this.iY2 : r.iY2,
				(new Color(super.getColor() & r.getColor())).getColor());		//iY2
		if(result.iX1>result.iX2)		{result.iX1=result.iX2=0;}
		if(result.iY1>result.iY2)		{result.iY1=result.iY2=0;}
		return result;
		
	}
	
	public static void main(String[] args) {
//Ã�â€œÃ�Â»Ã�Â°Ã�Â²Ã�Â½Ã�Â° Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½ 
//Ã�Â¡Ã‘Å Ã�Â·Ã�Â´Ã�Â°Ã�Â²Ã�Â° Ã�Â¾Ã�Â±Ã�ÂµÃ�ÂºÃ‘â€šÃ�Â¸, Ã�Â¸Ã�Â·Ã�Â²Ã�ÂµÃ�Â¶Ã�Â´Ã�Â°: 
		ColorRectangle oRect = new ColorRectangle(0,0,10,10,255);	//0000ff	000...11111111
		System.out.println(oRect.toString());
		
		//		Ã�ËœÃ�Â·Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â° Ã�Â¿Ã�Â¾ Ã�Â¥ Ã�Â¸ Y, Ã�Â¸Ã�Â·Ã�Â²Ã�ÂµÃ�Â¶Ã�Â´Ã�Â°, 
		oRect.translateX(10);
		System.out.println(oRect.toString());
		ColorRectangle oRect2 = new ColorRectangle(0,0,10,10,0);
		
//		Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â²Ã�Â° Ã�Â·Ã�Â° Ã�ÂµÃ�ÂºÃ�Â²Ã�Â¸Ã�Â²Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã‘â€šÃ�Â½Ã�Â¾Ã‘ï¿½Ã‘â€š,Ã�Â¸Ã�Â·Ã�Â²Ã�ÂµÃ�Â¶Ã�Â´Ã�Â° 
		
		boolean bRes = oRect.equals(oRect2);
		if(bRes) {
			System.out.println("equal OK");
		}else {
			System.out.println("equal FALSE");
		}
		
//		Ã�ËœÃ�Â·Ã‘â€¡Ã�Â¸Ã‘ï¿½Ã�Â»Ã‘ï¿½Ã�Â²Ã�Â° Ã�Â¾Ã�Â±Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¶Ã�Â´Ã�Â°Ã‘â€° Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¾Ã‘Å Ã�Â³Ã‘Å Ã�Â»Ã�Â½Ã�Â¸Ã�Âº, 
		ColorRectangle oRes=oRect.unionRect(oRect2);
		System.out.println("Union rect : " + oRes.toString());
		System.out.println("Area : " + oRes.calcArea());
		
//		Ã�Â¸Ã�Â·Ã�Â²Ã�ÂµÃ�Â¶Ã�Â´Ã�Â° Ã�ËœÃ�Â·Ã‘â€¡Ã�Â¸Ã‘ï¿½Ã�Â»Ã‘ï¿½Ã�Â²Ã�Â° Ã�Â¾Ã�Â±Ã‘â€° Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¾Ã‘Å Ã�Â³Ã‘Å Ã�Â»Ã�Â½Ã�Â¸Ã�Âº, Ã�Â¸Ã�Â·Ã�Â²Ã�ÂµÃ�Â¶Ã�Â´Ã�Â°
		ColorRectangle oResInters=oRect.intersectionRect(oRect2);
		System.out.println("Intersect rect : " + oResInters.toString());
		System.out.println("Area : " + oResInters.calcArea());
//		Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â²Ã�Â° Ã�Â·Ã�Â° Ã�Â¿Ã�Â¾-Ã�Â¼Ã�Â°Ã�Â»Ã�ÂºÃ�Â¾,Ã�Â¸Ã�Â·Ã�Â²Ã�ÂµÃ�Â¶Ã�Â´Ã�Â° 
		System.out.println("Compare : " + oRes.compareTo(oResInters));	
				
	}
	

}
