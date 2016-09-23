package com.zzy.frank.www.citylove_master.util;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class ValueAnim {

	
	/**
	 * ����view��ƽ�ƶ�����������X��Y�����ƽ��
	 * @param view	Ҫƽ�Ƶ�view
	 */
	public static void startValue(final View view,final String orientation,float from,float to){
		ValueAnimator anim = ValueAnimator.ofFloat(from,to);
		anim.setTarget(view);
		anim.setDuration(1000);
		anim.setInterpolator(new AccelerateInterpolator());
		anim.start();
		anim.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				if (orientation.equals("y")) {
					view.setTranslationY((Float)animation.getAnimatedValue());
				}else{
					view.setTranslationX((Float)animation.getAnimatedValue());
				}
			}
		});
	}
	
	/**
	 * ������
	 * @param view
	 * @param ratio  �ƶ�һ���ĸ߶���Ҫ�ƶ��Ŀ�ȵı�ֵ(���ڱ�ֵһֱ�޷���ȷ�������ڵ���ʱ�ͷ��������ֵ����������ʽ����)
	 * @param from
	 * @param to
	 */
	public static void startValue(final View view,final float ratio,float from,float to){
		ValueAnimator anim = ValueAnimator.ofFloat(from,to);
		anim.setTarget(view);
		anim.setDuration(2000);
		anim.setInterpolator(new AccelerateInterpolator());
		anim.start();
		anim.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (Float) animation.getAnimatedValue();//value��ֵ��Χ��0-1
				view.setTranslationX(200 * value * 3);//���������þ��ᣬ��������ʽ�������������ߵ�x��yλ�Ƽ��㹫ʽ��
				view.setTranslationY(0.5f * 200 * value*3 * value * 3);
			}
		});
		
	}
	
}
