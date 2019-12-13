/**
 * Copyright (c) 2016-present, RxJava Contributors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */
package io.reactivex;

import io.reactivex.annotations.*;

/**
 * Represents a basic {@link Maybe} source base interface,
 * consumable via an {@link MaybeObserver}.
 * <p>
 * This class also serves the base type for custom operators wrapped into
 * Maybe via {@link Maybe#create(MaybeOnSubscribe)}.
 *
 * @param <T> the element type
 * @since 2.0
 */
public interface MaybeSource<T> {

  /**
   * Subscribes the given MaybeObserver to this MaybeSource instance.
   *
   * @param observer the MaybeObserver, not null
   * @throws NullPointerException if {@code observer} is null
   */
  void subscribe(@NonNull MaybeObserver<? super T> observer);
}
