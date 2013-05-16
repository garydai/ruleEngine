---
layout: default
title: C++ 智能指针

---

##what
是个对象,不是数据类型,对象里有个普通指针和内存被引用个数	
std::auto_ptr  
boost::scoped_ptr  
boost::shared_ptr  
boost::scoped_array  
boost::shared_array  
boost::weak_ptr  boost:: intrusive_ptr

##why
普通指针缺点:  
普通指针指向的内存需要手动delete  
1.	内存被多次delete导致程序crash  
2.	内存没被delete导致内存泄露  
##how

##实现

	class ptr
	{
		friend class smart_pointer;
		int count;
		int* pointer;
	};
	class smart_pointer
	{
	public:
		smart_pointer(arguments);
		~smart_pointer();
		//含参数构造函数
		smart_pointer(int* p)
		{
			pointer = p;
			pointer.count = 1;
		}
		smart_pointer()
		{
			pointer.pointer = NULL;
			pointer.count = 0;
		}
		//赋值操作
		smart_pointer& operator=(smart_pointer& p)
		{
			p.pointer.count ++;
			if(pointer.count)
			{
				pointer.count --;
				if(pointer.count == 0)
				{
					delete pointer.pointer;
					pointer.pointer = NULL;
				}
			}
			pointer.pointer = p.pointer;
			pointer.count = p.count;
			return *this;
		}
		//拷贝构造函数
		smart_pointer(smart_pointer& p)
		{
			pointer.pointer = p.pointer;
			pointer.count = p.count;
		}
	private:
		ptr pointer;
		/* data */
	};

