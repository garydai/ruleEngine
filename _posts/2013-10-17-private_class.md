---
layout: default
title: C++类的私有权限

---

##C++类的私有权限




在拷贝构造函数中为什么可以访问引用对象的私有变量？

例如:

	class Point
	{	
	public:
    	    Point(int xx=0,int yy=0){X=xx;Y=yy;}
    	    Point(Point &p);
	private:
	        int X,Y;

	};
	Point::Point(Point &p)
	{
	        X=p.X;
	        Y=p.Y;
	}

C++类的私有权限是针对类的而不是针对类的对象。

其他类不能访问本类的私有成员，而不是本类的其他对象不能访问该对象的私有成员。

在本类的成员函数中能访问该类的私有成员。一个类的对象不是类的成员，所以它不能访问私有成员。