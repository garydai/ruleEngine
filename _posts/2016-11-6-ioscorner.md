---
layout: default

title: ios 圆角

---

##ios性能优化


很多 CPU 的操作都会延迟 GPU 开始渲染的时间：

布局的计算 - 如果你的视图层级太过于复杂，或者视图需要重复多次进行布局，尤其是在使用 Auto Layout 进行自动布局时，对性能影响尤为严重；

视图的惰性加载 - 在 iOS 中只有当视图控制器的视图显示到屏幕时才会加载；

解压图片 - iOS 通常会在真正绘制时才会解码图片，对于一个较大的图片，无论是直接或间接使用 UIImageView 或者绘制到 Core Graphics 中，都需要对图片进行解压；


ASDK 对于绘制过程的优化有三部分：分别是栅格化子视图、绘制图像以及绘制文字。

###查看性能工具
使用Core Animation工具
###图层混合

color a值不要设，设置背景色
###慎用离屏渲染
####ios圆角性能优化


###AsyncDisplayKit
	对应表
	UIKit				AsyncDisplayKit
	UIView				ASDisplayNode
	UILabel				ASTextNode
	UIImageView				ASImageNode
	UIButton					ASButtonNode
	UITableView				ASTableView
	UICollectionView			ASCollectionView
	UITableViewCell			ASCellNode
	UICollectionViewCell	ASCellNode
	

masksToBounds离屏渲染带来的性能损耗

	
	
http://www.jianshu.com/p/619cf14640f3





    


