package org.apache.commons.proxy2.stub;

/**
 * Version 1.0
 * Created by lll on 17/6/30.
 * Description
 * copyright generalray4239@gmail.com
 */

public abstract class Trainer<T> extends BaseTrainer<Trainer<T>, T> {

  protected Trainer() {
    super();
  }

  protected Trainer(Class<T> traineeType) {
    super(traineeType);
  }

}