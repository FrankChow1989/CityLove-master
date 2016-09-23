package com.zzy.frank.www.citylove_master.util;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class ObjectAnim {
	
	/**
	 * ��������ֵ���ò�ͬ�����Զ���
	 * @param view  ���Ե�������
 	 * @param property  ��������
	 * @param from  ��ʼֵ
	 * @param to	����ֵ
	 */
	public static void startObj(View view,String property,float from , float to){
		ObjectAnimator.ofFloat(view, property, from , to)
						.setDuration(500)
						.start();
	}
	
	/**
	 * ͨ��ObjectAnimatorʵ�ֶද��ͬʱ����
	 * @param view	 Ҫ������view
	 * @param from	 ��ʼֵ
	 * @param to	 ����ֵ
	 */
	public static void startObj(final View view,float from ,float to){
		//���ｫ������Ϊocean����ΪAndroid����ʶ������ԣ����Բ������κθı䣬������Ҫ��from��to�����Ծ������趨��
		ObjectAnimator anim = ObjectAnimator.ofFloat(view, "ocean", from , to);//����Ĳ�����������������������С����ˡ�
		anim.setDuration(2000);
		anim.start();
		anim.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float x = (Float) animation.getAnimatedValue();
				view.setAlpha(x);
				view.setScaleX(x);
				view.setScaleY(x);
			}
		});
	}
	
	/**
	 * ʹ��PropertyValuesHolder��ʵ��ObjectAnimator�Ķ����Զ���
	 * @param view	Ҫ�����Ŀؼ�
	 */
	public static void startObj(View view){
		//������Լ��϶�rotation���ж�
		ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("scaleX",1.0f, 0.0f,1.0f),//����Ĳ���������������С���
                PropertyValuesHolder.ofFloat("scaleY", 1.0f,0.0f,1.0f),
                PropertyValuesHolder.ofFloat("alpha", 1.0f,0.0f,1.0f)
        ).setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
	}
}
